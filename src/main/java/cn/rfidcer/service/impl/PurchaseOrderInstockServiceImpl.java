package cn.rfidcer.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.InstockPurchaseItem;
import cn.rfidcer.bean.Origin;
import cn.rfidcer.bean.PurchaseInstockItem;
import cn.rfidcer.bean.PurchaseInstockOrder;
import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Slaughterhouse;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.AreaInfoMapper;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.InstockItemMapper;
import cn.rfidcer.dao.InstockMainMapper;
import cn.rfidcer.dao.OriginMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.PurchaseOrderDao;
import cn.rfidcer.dao.SlaughterhouseMapper;
import cn.rfidcer.dao.SupplierMapper;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.PurchaseOrderInstockService;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.util.BatchNumberUtil;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

/**   
*    
* 项目名称：trace   
* 类名称：PurchaseOrderInstockServiceImpl   
* 类描述：   
* 创建人：JUGUANGXING  
* 创建时间：2016年3月10日 上午10:14:14   
* 修改人：honest   
* 修改时间：2016年3月10日 上午10:14:14   
* 修改备注：   
* @version    
*    
*/
@Service
public class PurchaseOrderInstockServiceImpl  implements PurchaseOrderInstockService{
	
	private Logger logger=LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private GoodsDao goodsDao;  
	@Autowired
	private InstockItemMapper iiMapper;
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	@Autowired
	private InstockMainMapper immapper;
	@Autowired
	private ProductMapper pmapper;
	@Autowired
	private CompanyMapper  companyMapper;
	@Autowired
	private SlaughterhouseMapper  slaughterhouseMapper;
	@Autowired
	private SupplierMapper  supplierMapper;
	@Autowired
	private TraceCodeService traceCodeService;
	@Autowired
	private TraceTypeService traceTypeService;

	@Autowired
	private OriginMapper originMapper;
	
	public ResultMsg dataVerification(PurchaseInstockOrder purchaseInstockOrder){
		ResultMsg msg = new ResultMsg();
		msg.setCode("1");
		PurchaseOrder   purchaseOrder= purchaseInstockOrder.getPurchaseOrder();
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		if(purchaseOrder.getSupplierId()== null || purchaseOrder.getSupplierId().equals("") || supplierMapper.selectByPrimaryKey(purchaseOrder.getSupplierId())==null){
			msg.setCode("0");
			msg.setMsg("请选择对应的供应商！");
			return  msg;
		}
		int i = 0 ;
		for(Goods goods : purchaseInstockOrder.getLstgoods())
		{
			i++;
			if(goods.getProductId()== null || goods.getProductId().equals("") || pmapper.selectByPrimaryKey(goods.getProductId())==null){
				msg.setCode("0");
				msg.setMsg("第"+i+"行,请选择对应的产品！");
				return  msg;
			}
			if(goods.getAreaInfoId()!= null && !goods.getAreaInfoId().equals("") && areaInfoMapper.selectByPrimaryKey(goods.getAreaInfoId())==null){
				msg.setCode("0");
				msg.setMsg("第"+i+"行,请选择对应的产地！");
				return  msg;
			}
			if(goods.getSlaughterhouseId()!= null && !goods.getSlaughterhouseId().equals("") && slaughterhouseMapper.selectByPrimaryKey(goods.getSlaughterhouseId())==null){
				msg.setCode("0");
				msg.setMsg("第"+i+"行,请选择对应的屠宰场！");
				return  msg;
			}
		}
		return msg;
	}
	public ResultMsg dataVeriRepeat(PurchaseInstockOrder purchaseInstockOrder){
		ResultMsg rmsg = new ResultMsg();
		rmsg.setCode("1");
		int iPurchaseOrderRepeatCount = purchaseOrderDao.findRepeat(purchaseInstockOrder.getPurchaseOrder());//采购单重复数量
		if(iPurchaseOrderRepeatCount>0){
			rmsg.setCode("0");
			rmsg.setMsg("采购单号重复");
			return  rmsg;
		}
		int iInstockMainRepeatCount = immapper.findRepeat(purchaseInstockOrder.getInstockMain());//采购单重复数量
		if(iInstockMainRepeatCount>0){
			rmsg.setCode("0");
			rmsg.setMsg("入库号重复");
			return  rmsg;
		}
		return rmsg;
		
	}
	@Override
	public ResultMsg addOrUpdate(PurchaseInstockOrder purchaseInstockOrder,User user) {
		
		//校验数据-校验下拉列表框值是否合法
		ResultMsg  rmsg = dataVerification(purchaseInstockOrder);
		if(rmsg.getCode().equals("0")){
			return rmsg;
		}
		int res=0;
		String msg=null;
		logger.info("进入页面："+purchaseInstockOrder);
		PurchaseOrder purchaseOrder = purchaseInstockOrder.getPurchaseOrder();
		InstockMain instockmain = purchaseInstockOrder.getInstockMain();
		purchaseOrder.setOrderTime(instockmain.getRegistDate());
		purchaseOrder.setRegistrant(instockmain.getRegistrant());

		try{
			//新增数据
			if(StringUtils.isEmpty(purchaseOrder.getPurchaseOrderId())){
				
				rmsg = dataVeriRepeat(purchaseInstockOrder);
				if(rmsg.getCode().equals("0")){
					return rmsg;
				}
				// purchaseOrder.setOrderTime(new Timestamp(System.currentTimeMillis()));
				String pUUID= UUIDGenerator.generatorUUID();
				purchaseOrder.setPurchaseOrderId(pUUID);
				logger.info("新增采购商品单："+purchaseOrder);
				msg="新增采购商品单";
				CommonImportUtils.setCreateAndUpdateTime(purchaseOrder, user);//设置修改日期
				res = purchaseOrderDao.addPurchaseOrder(purchaseOrder);
				if(res==1){//如果采购单添加成功
					instockmain.setCompanyId(purchaseOrder.getCompanyId());// 设置用户企业ID
					//如果批次没有手动输入自动创建
					if(StringUtils.isEmpty(instockmain.getInstockBatchNum())){
						instockmain.setInstockBatchNum(BatchNumberUtil.getGoodsBatch());
					}
					String mUUID= UUIDGenerator.generatorUUID();
					instockmain.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());// 设置采购单ID
					instockmain.setInstockMainId(mUUID);//设置入库单ID
					instockmain.setStatus("0");//入库状态
					CommonImportUtils.setCreateAndUpdateTime(instockmain, user);//设置修改日期
					//添加入库单
					res=immapper.insert(instockmain);
					//重新设置入库单
					purchaseInstockOrder.setInstockMain(instockmain);
					//如果入库单添加完成
					if(res==1){
						addPurchaseInstockItems(purchaseInstockOrder, user);
					}
				}
			}else{
				//修改入库表
				CommonImportUtils.setUpdateTime(instockmain, user);//设置修改日期
				logger.info("修改入库单："+instockmain);
				immapper.updateByPrimaryKeySelective(instockmain);
				CommonImportUtils.setUpdateTime(purchaseOrder, user);//设置修改日期
				logger.info("修改采购单："+purchaseOrder);
				purchaseOrderDao.updatePurchaseOrder(purchaseOrder);
				List<InstockItem> instockitems = instockmain.getInstockitems();
				List<Goods> lstgoods = purchaseInstockOrder.getLstgoods();
				for (int i=0;i<lstgoods.size();i++) {
					Goods goods=lstgoods.get(i);
					if(StringUtils.isEmpty(goods.getGoodsId()))
					{
						InstockItem instockItem = instockitems.get(i);
						insertGoods(goods,instockItem,instockmain.getInstockMainId(),user);
					}else{
						//goods.getNum()  数量重新设置
						for (InstockItem  ikItem: purchaseInstockOrder.getInstockMain().getInstockitems()) {
							if(goods.getGoodsId().equals(ikItem.getGoodsId())){
								//修改商品入库详细
								InstockItem  instockitem2 = new InstockItem();
								instockitem2=iiMapper.selectByPrimaryKey(ikItem.getInstockItemId());
								CommonImportUtils.setUpdateTime(ikItem, user);//设置修改日期
								iiMapper.updateByPrimaryKeySelective(ikItem);
								CommonImportUtils.setUpdateTime(goods, user);//设置修改日期
								//计算数量增加还是减少
								int nNum =  Integer.parseInt(ikItem.getInstockNum())-Integer.parseInt(instockitem2.getInstockNum());
								//修改传入的值
								BigDecimal bgnum = new BigDecimal(nNum);
								goods.setNum(bgnum);//修改数量的值
								Goods nGoods = goodsDao.getByGoodsId(goods.getGoodsId());
								goods.setNum(nGoods.getNum().add(goods.getNum()));
								goodsDao.updateGoods(goods);
							}
						}
						
					}
				}
				
				
				
				res = 1; 
				
				msg="修改采购商品";
			}
		}catch (Exception e) {
			res = -1; 
			msg="操作失败，数据存在异常";
		}
		return ResultMsgUtil.getReturnMsg(res,msg);
	}
	
	@Override
	public ResultMsg deletePurchaseItem(String purchaseItemId,String instockItemId,String goodsId,String num) {
		int res=0;
		String msg=null;
		try{
			//logger.info("删除采购单,ID:"+purchaseItemId);
			//int res2 = purchaseItemMapper.deleteByPrimaryKey(purchaseItemId);
			logger.info("删除商品入库明细单,ID:"+instockItemId);
			iiMapper.deleteByPrimaryKey(instockItemId);
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);
			BigDecimal bgnum = new BigDecimal(Integer.parseInt(num));
			goods.setNum(bgnum);
			Goods nGoods = goodsDao.getByGoodsId(goodsId);
			nGoods.setNum(nGoods.getNum().subtract(goods.getNum()));
			goodsDao.updateGoods(nGoods);
			res = 1;
			msg="删除采购单";
		}
		catch (Exception e) {
			res = -1; 
			msg="操作失败，数据存在异常";
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}
	/**
	 * 添加商品和入库详细单
	 * @param goods
	 * @param instockMainId
	 * @param user
	 */
	private void insertGoods(Goods goods,InstockItem inItem,String instockMainId, User user) {
		
		//添加商品
		CommonImportUtils.setCreateAndUpdateTime(goods, user);//设置修改日期
		//获取企业Code
		String strCompanyCode = companyMapper.selectByPrimaryKey(goods.getCompanyId()).getCode();
		//设置商品Code
		String goodeCode  = traceCodeService.newTraceCode(goods.getCompanyId(), strCompanyCode);
		goods.setCode(goodeCode);
		String goodsId  = createOrUpdateGoods(goods,user);
		//添加入库详细单
		InstockItem  instockitem = new  InstockItem();
		instockitem.setGoodsId(goodsId);
		CommonImportUtils.setCreateAndUpdateTime(instockitem, user);//设置修改日期
		instockitem.setInstockNum(goods.getNum().toString());
		instockitem.setInstockMainId(instockMainId);//设置入库表的ID
		instockitem.setInstockItemId(UUIDGenerator.generatorUUID());//设置商品详细表的ID
		String originId = inItem.getOriginId();
		if(!StringUtils.isEmpty(originId)) {
			instockitem.setOriginId(originId);
		}
		iiMapper.insert(instockitem);	
	}
	/**
	 * 添加采购详详细单
	 * @param purchaseItem
	 * @param purchaseOrderId
	 * @param user
	 */
	private void insertPurchaseItem(PurchaseItem purchaseItem,String purchaseOrderId, User user) {
		CommonImportUtils.setCreateAndUpdateTime(purchaseItem, user);//设置修改日期
		purchaseItem.setPurchaseItemId(UUIDGenerator.generatorUUID());
		purchaseItem.setPurchaseOrderId(purchaseOrderId);
		purchaseOrderDao.addPurchaseItem(purchaseItem);
	}
	/**
	 * 添加采购详细和商品以及入库详细单
	 * @param purchaseInstockOrder
	 * @param user
	 */
	private void addPurchaseInstockItems(PurchaseInstockOrder purchaseInstockOrder, User user) {
		PurchaseOrder purchaseOrder = purchaseInstockOrder.getPurchaseOrder();
		InstockMain instockmain = purchaseInstockOrder.getInstockMain();
		for (PurchaseItem  purchaseItem: purchaseOrder.getPurchaseItems()) {
			insertPurchaseItem(purchaseItem,purchaseOrder.getPurchaseOrderId(), user);
		}
		List<InstockItem> instockitems = instockmain.getInstockitems();
		List<Goods> lstgoods = purchaseInstockOrder.getLstgoods();
		for (int i=0;i<lstgoods.size();i++) {
			Goods goods=lstgoods.get(i);
			InstockItem instockItem = instockitems.get(i);
			insertGoods(goods,instockItem,instockmain.getInstockMainId(),user);
		}
	}
	public String createOrUpdateGoods(Goods goods, User user) {
		String myUUID ="";
		Goods nGoods = goodsDao.findByProductidStandarddetailidBatch(goods.getProductId(),goods.getProductStandardDetailId(),goods.getGoodsBatch());
		if (StringUtils.isEmpty(goods.getGoodsId() )) {
			// case: add
			if(nGoods==null){
				myUUID = UUIDGenerator.generatorUUID();
				goods.setGoodsId(myUUID);
				int res = goodsDao.insertSelective(goods);
				if(res==1){
					traceTypeService.insertTraceType(goods.getCode(), TraceType.GOODS, user.getId());
				}
			}else{
				//因为存在相同数据所以追加商品数量
				myUUID = nGoods.getGoodsId();
				nGoods.setNum(nGoods.getNum().add(goods.getNum()));
				goodsDao.updateGoods(nGoods);
			}
		} else {
			// case: update
			if(nGoods==null){
				goodsDao.updateGoods(goods);
				myUUID = goods.getGoodsId();
			}else{
				//因为存在相同数据所以追加商品数量
				nGoods.setNum(nGoods.getNum().add(goods.getNum()));
				int res = goodsDao.insertSelective(nGoods);
				if(res==1){
					traceTypeService.insertTraceType(goods.getCode(), TraceType.GOODS, user.getId());
				}
				myUUID = nGoods.getGoodsId();
			}
		}
		return myUUID;
	}
	
	
	@Override
	public List<PurchaseInstockOrder> listPurchaseInstockOrder(Page page,String companyId,String instockType) {
		return purchaseOrderDao.listPurchaseInstockOrder(page, companyId, instockType);
	}
	
	@Override
	public ResultMsg delPurchaseInstockOrder(String purchaseOrderId) {
		logger.info("删除采购单,ID:"+purchaseOrderId);
		int res = 0 ;
		String msg ="删除采购单";
		try{
			
			List<PurchaseInstockItem> lst = purchaseOrderDao.findPurchaseInstockItemsByOrderId(purchaseOrderId);
			for(PurchaseInstockItem item :lst ){
				
				BigDecimal bgnum = new BigDecimal(item.getQuantity());
				Goods nGoods = goodsDao.getByGoodsId(item.getGoodsId());
				nGoods.setNum(nGoods.getNum().subtract(bgnum));
				goodsDao.updateGoods(nGoods);
			}
			//删除商品详细列数据
			iiMapper.deleteInstockItemsByPurchaseOrderId(purchaseOrderId);
			//删除入库数据
			immapper.deleteInstockMainByPurchaseOrderId(purchaseOrderId);
			//删除采购单详细数据
			purchaseOrderDao.deletePurchaseItemsByPurchaseOrderId(purchaseOrderId);
			//删除采购单数据
			res = purchaseOrderDao.deletePurchaseOrder(purchaseOrderId);
			res = 1; 
		}catch (Exception e) {
			res = -1; 
			msg="操作失败，数据存在异常";
		}

		
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}
	
	@Override
	public List<PurchaseItem> findPurchaseItemsByOrderId(String purchaseOrderId) {
		List<PurchaseItem> lst = purchaseOrderDao.findPurchaseItemsByOrderId(purchaseOrderId);
		return lst;
	}

	@Override
	public List<PurchaseInstockItem> findPurchaseInstockItemsByOrderId(String purchaseOrderId) {
		List<PurchaseInstockItem> lst = purchaseOrderDao.findPurchaseInstockItemsByOrderId(purchaseOrderId);
		for(int i = 0 ; i< lst.size();i++){
			PurchaseInstockItem  item = lst.get(i);
			AreaInfo  area = areaInfoMapper.selectByPrimaryKey(item.getAreaInfoId());
			//怕没有地区 所查询一下有就赋值
			if(area!=null){
				item.setCatgName(area.getCatgName());
			}
			//怕没有屠宰场ID 所查询一下有就赋值
			Slaughterhouse  sh  =  slaughterhouseMapper.selectByPrimaryKey(item.getSlaughterhouseId());
			if(sh!=null){
				item.setSlaughterhouseName(sh.getSlaughterhouseName());
			}
		}
		return lst;
	}
	
	
	@Override
	public List<InstockPurchaseItem> findInstockItemsByOrderId(Page page,String purchaseOrderId) {
		List<InstockPurchaseItem> lst = iiMapper.findInstockItemsByOrderId(page,purchaseOrderId);
		for(int i = 0 ; i< lst.size();i++){
			InstockPurchaseItem  item = lst.get(i);
			AreaInfo  area = areaInfoMapper.selectByPrimaryKey(item.getAreaInfoId());
			//怕没有地区 所查询一下有就赋值
			if(area!=null){
				item.setCatgName(area.getCatgName());
			}
			//怕没有屠宰场ID 所查询一下有就赋值
			Slaughterhouse  sh  =  slaughterhouseMapper.selectByPrimaryKey(item.getSlaughterhouseId());
			if(sh!=null){
				item.setSlaughterhouseName(sh.getSlaughterhouseName());
			}
			Origin origin = originMapper.selectByPrimaryKey(item.getOriginId());
			if(origin!=null){
				item.setOriginName(origin.getOriginName());
			}
		}
		return lst;
	}
	
	
	@Override
	public List<PurchaseOrder> listAll() {
		return purchaseOrderDao.listAll();
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.PurchaseOrderInstockService#listPurchaseInstockOrder(cn.rfidcer.interceptor.Page, cn.rfidcer.bean.PurchaseOrder)
	 */
	@Override
	public List<PurchaseInstockOrder> listPurchaseInstockOrder(Page page, PurchaseOrder purchaseOrder) {
		return null;
	}
	

}
