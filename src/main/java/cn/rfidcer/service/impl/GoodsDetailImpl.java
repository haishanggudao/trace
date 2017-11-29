package cn.rfidcer.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.UserToken;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.GoodsDetailDao;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.service.GoodsDetailService;
import cn.rfidcer.service.QRCodeService;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.MyStringUtls;
import cn.rfidcer.util.TokenCacheUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class GoodsDetailImpl implements GoodsDetailService {

	private Logger logger = LoggerFactory.getLogger(GoodsDetailImpl.class);

	@Autowired
	private GoodsDetailDao goodsDetailDao;

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private TokenCacheUtil tokenCacheUtil;

	@Autowired
	private QRCodeService qrCodeService;

	@Autowired
	private TraceCodeService traceCodeService;

	@Autowired
	private TraceTypeService traceTypeService;
	
	@Autowired
	private SysVariableDao sysVariableDao;

	@Override
	public ResultMsg getGoodsByQRCode(String qrcode, UserToken userToken) {
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		try {
			String decode = URLDecoder.decode(qrcode, "UTF-8");
			System.out.println(decode);
			logger.info("扫描二维码获取商品信息：{}", decode);
			if (tokenCacheUtil.compareUserTokenFromCache(userToken)) {
				GoodsDetail goodsByQRCode = goodsDetailDao
						.getGoodsByQRCode(MyStringUtls.subQRCode(decode));
				if (goodsByQRCode != null) {
					// 手持机查看扫描时间
					goodsByQRCode.setScanTime(DateUtil.getToday());
					code = "1";
					resultMsg.setBaseEntity(goodsByQRCode);
				} else {
					code = "2";
					msg = "无数据";
				}
			} else {
				code = "-1";
				msg = "Token过期,请重新登录";
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("转码异常", e);
			code = "-2";
			msg = "转码异常";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public GoodsDetail createNewGoodsDetail(String companyId,
			String strCompanyCode, String goodsId, User currentUser) {
		GoodsDetail goodsDetail = new GoodsDetail();
		goodsDetail.setGoodsDetailId(UUIDGenerator.generatorUUID());
		goodsDetail.setGoodsId(goodsId);
		goodsDetail.setCompanyId(companyId);
		// 设置商品Code
		String goodsCode = traceCodeService.newTraceCode(companyId,
				strCompanyCode);

		goodsDetail.setCode(goodsCode);
		CommonImportUtils.setCreateAndUpdateTime(goodsDetail, currentUser,
				new Timestamp(System.currentTimeMillis()));
		int res = goodsDetailDao.addOrUpdateGoodsDetail(goodsDetail);
		if (res == 1) {
			traceTypeService.insertTraceTypeByDeliverType(goodsId, currentUser,
					goodsCode);
		}
		return goodsDetail;
	}

	@Override
	public GoodsDetail createNewGoodsDetailWithBarcode(String companyId,
			String strCompanyCode, String goodsId, String barcode,
			User currentUser) {
		GoodsDetail goodsDetail = new GoodsDetail();
		goodsDetail.setGoodsDetailId(UUIDGenerator.generatorUUID());
		goodsDetail.setGoodsId(goodsId);
		goodsDetail.setCompanyId(companyId);
		// 设置商品Code
		String goodsCode = traceCodeService.newTraceCode(companyId,
				strCompanyCode);
		goodsDetail.setCode(goodsCode);

		// assign value to barcode
		goodsDetail.setBarcode(barcode);

		CommonImportUtils.setCreateAndUpdateTime(goodsDetail, currentUser,
				new Timestamp(System.currentTimeMillis()));

		// DAO action: insert a new goods detail
		int res = goodsDetailDao.addGoodsDetailWithBarcode(goodsDetail);
		if (res == 1) {
			traceTypeService.insertTraceTypeByDeliverType(goodsId, currentUser,
					goodsCode);
		}
		return goodsDetail;
	}

	@Override
	public ArrayList<File> createOrUpdateGoodsDetail(GoodsDetail detail,
			int qrNum, User currentUser, int multiImg) {
		String name = null;
		try {
			name = URLDecoder.decode(detail.getProductName(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("产品名称解码异常：", e1);
		}
		logger.info("产品名称：{}", name);
		List<GoodsDetail> detailList = goodsDetailDao
				.getGoodsDetailsByGoodsId(detail.getGoodsId());
		int length = 0;
		ArrayList<File> files = new ArrayList<File>();
		if (detailList.isEmpty()) {
			length = qrNum;
		} else {
			length = qrNum - detailList.size();
		}
		try {
			String companyId = detail.getCompanyId();
			String goodsId = detail.getGoodsId();
			String strCompanyCode = companyMapper.selectByPrimaryKey(companyId)
					.getCode();
			// 没有商品详细则追加和重新生成
			for (int i = 0; i < length; i++) {
				GoodsDetail goodsDetail = createNewGoodsDetail(companyId,
						strCompanyCode, goodsId, currentUser);
				detailList.add(goodsDetail);
			}
			int addCount = 0;// 填补
			if (detailList.size() % multiImg != 0) {
				addCount = multiImg - detailList.size() % multiImg; // 计算+多少个可以并排
			}
			// 填充空白数组
			for (int j = 0; j < addCount; j++) {
				GoodsDetail goodsDetail = new GoodsDetail();
				detailList.add(goodsDetail);
			}
			files = qrCodeService.getMultiRowQRCodeFiles(detailList, multiImg,
					name);
		} catch (Exception e) {
			logger.error("生成二维码异常：", e);
		}
		return files;
	}

	@Override
	public List<GoodsDetail> createGoodsDetail(Goods goods, User currentUser) {
		String goodsId = goods.getGoodsId();
		int qrNum = goods.getNum().toBigInteger().intValue();
		logger.info("获取明细二维码：{},数量：{}",goodsId,qrNum);
		List<GoodsDetail> detailList = goodsDetailDao.getGoodsDetailsByGoodsId(goodsId);

		int length = 0;

		if (detailList.isEmpty()) {
			length = qrNum;
		} else {
			length = qrNum - detailList.size();
		}

		String companyId = goods.getCompanyId();
		String strCompanyCode = companyMapper.selectByPrimaryKey(companyId)
				.getCode();
		// 没有商品详细则追加和重新生成
		for (int i = 0; i < length; i++) {
			GoodsDetail goodsDetail = createNewGoodsDetail(companyId,
					strCompanyCode, goodsId, currentUser);
			detailList.add(goodsDetail);
		}
		return detailList;
	}
	
	public List<GoodsDetail> createGoodsDetailAndUrl(Goods goods, User currentUser) {
		String goodsId = goods.getGoodsId();
//		String url="http://192.168.8.39:8080/trace/ws/getTrace/";
		String url= sysVariableDao.getValByKey("QRCodePrev");
		int qrNum = goods.getNum().toBigInteger().intValue();
		logger.info("获取明细二维码：{},数量：{}",goodsId,qrNum);
		List<GoodsDetail> detailList = goodsDetailDao.getGoodsDetailsByGoodsId(goodsId);
		int lengths = 0;

		if (detailList.isEmpty()) {
			lengths = qrNum;
		} else {
			lengths = qrNum - detailList.size();
		}

		String companyId = goods.getCompanyId();
		String strCompanyCode = companyMapper.selectByPrimaryKey(companyId)
				.getCode();
		// 没有商品详细则追加和重新生成
		for (int i = 0; i < lengths; i++) {
			GoodsDetail goodsDetail = createNewGoodsDetail(companyId,
					strCompanyCode, goodsId, currentUser);
			detailList.add(goodsDetail);
		}
		for (GoodsDetail detailLists : detailList) {
			String CodeAndUrl=url+detailLists.getCode();
			detailLists.setCode(CodeAndUrl);
		}
		System.out.println(detailList);
		return detailList;
	}

}
