package cn.rfidcer.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.lingala.zip4j.core.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.BarcodeService;
import cn.rfidcer.service.CommonVariableService;
import cn.rfidcer.service.GoodsDetailService;
import cn.rfidcer.service.GoodsService;
import cn.rfidcer.service.QRCodeService;
import cn.rfidcer.service.VariableService;
import cn.rfidcer.util.DateUtil;

import com.alibaba.fastjson.JSONObject;

/**
* @Title: GoodsController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 商品信息; 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:59:02 
* @version V1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsDetailService goodsDetailService;

	@Autowired
	private QRCodeService qrCodeService;
	
	@Autowired
	private BarcodeService barcodeService;
	
	@Autowired
	private CommonVariableService commonVariableService;
	
	@Autowired
	private VariableService variableService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(CompanyPage companyPage) {
		return "goods";
	}

	@RequestMapping(value = "/getgoods")
	@ResponseBody
	public List<Goods> list(Page page, Goods goods, String producttype) {
		List<Goods> myGoods =  goodsService.getGoodsList(page, goods, producttype);
		return myGoods;
	}

	/**
	 * 获取所有的商品信息;
	 * @param page
	 * @param goods
	 * @param producttype
	 * @return
	 */
	@RequestMapping(value = "/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:goods:list","base:goodsmaterial:list"},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page, Goods goods, String producttype) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Goods> myGoods = goodsService.getGoodsList(page, goods, producttype);
		map.put("rows", myGoods);
		return map;
	}
	
	/**
	 * 查询所有的商品信息;
	 * @param page
	 * @param goods
	 * @param producttype
	 * @return
	 */
	@RequestMapping(value = "/findAllQueryList")
	@ResponseBody
	@RequiresPermissions(value={"base:goods:list","base:goodsmaterial:list"},logical=Logical.OR)
	public Map<String, Object> findAllQueryList(Page page, Goods goods, String producttype) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Goods> myGoods = goodsService.getGoodsList(page, goods, producttype);
		map.put("rows", myGoods);
		return map;
	}
	
	/**
	 * 新增或编辑商品信息;
	 * @param goods
	 * @param currentUser
	 * @return
	 */
	@RequiresPermissions(value={"base:goods:add","base:goods:edit","base:goodsmaterial:add","base:goodsmaterial:edit"},logical=Logical.OR)
	@RequestMapping(value = "/addgoods", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg createOrUpdateGoods(Goods goods, @CurrentUser User currentUser) {
		return goodsService.createOrUpdateGoods(goods, currentUser);
	}
	
	/**
	 * 删除商品信息;
	 * @param goods
	 * @return
	 */
	@RequiresPermissions(value={"base:goods:delete","base:goodsmaterial:delete"},logical=Logical.OR)
	@RequestMapping(value = "/delgoods", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg delGoods(Goods goods) {
		return goodsService.delGoods(goods);
	}
	
	/**
	 * 设置用完;
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/setusable", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:goodsmaterial:usable"},logical=Logical.OR)
	public ResultMsg setUsable(Goods goods) {
		return goodsService.updateGoodsUsable(goods);
	}
	
	/**
	 * 查询所有的商品名称;
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/findAllGoodsName", method = RequestMethod.POST)
	@ResponseBody
	public List<Goods> findAllGoodsName(String companyId) {
		return goodsService.findAllGoodsName(companyId);
	}
	
	/**
	 * 下载商品二维码
	 * 
	 * @param saleOrder
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadqc")
	@RequiresPermissions(value={"base:goods:createQRCode"},logical=Logical.OR)
	public ResponseEntity<byte[]> downloadqc(GoodsDetail detail, int qrNum, int multiImgCount, @CurrentUser User currentUser)
			throws IOException {
		ArrayList<File> list = goodsDetailService.createOrUpdateGoodsDetail(detail, qrNum, currentUser, multiImgCount);
		ZipFile zipFile = qrCodeService.getZipFile(list);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", zipFile.getFile().getName());
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(zipFile.getFile()), headers,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getQRCode",produces={"application/json;charset=UTF-8"})
	@RequiresPermissions(value={"base:goods:createQRCode"},logical=Logical.OR)
	@ResponseBody
	public List<GoodsDetail> getQRCodes(Goods goods,@CurrentUser User currentUser){
		return goodsDetailService.createGoodsDetail(goods, currentUser);
	}
	
	/**
	 * 下载条形码;
	 * @param detail
	 * @param qrNum
	 * @param currentUser
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadBarcode")
	public ResponseEntity<byte[]> downloadBarcode(GoodsDetail detail, int qrNum, @CurrentUser User currentUser) 
			throws IOException{
		ArrayList<File> list = barcodeService.createOrUpdateGoodsDetail(detail, qrNum, currentUser);
		ZipFile zipFile = barcodeService.getZipFile(list, detail);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", zipFile.getFile().getName());
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(zipFile.getFile()), headers,
				HttpStatus.CREATED);
	}

	@RequestMapping(value="/getBarCodes",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true)
	public List<GoodsDetail> getBarCodes(GoodsDetail detail, int qrNum, @CurrentUser User currentUser){
		return barcodeService.createDetailList(detail, qrNum, currentUser);
	}
	
	/**
	 * 下载追溯码;
	 * @param strJson
	 * @param currentUser
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadcode")
	@RequiresPermissions(value={"base:goods:downQRCode"})
	public void  downloadCode( String strJson,String companyId,@CurrentUser User currentUser, final HttpServletResponse response) throws IOException {
		String fileName = "downloadcode_" + DateUtil.timestamp() + ".txt";
		strJson = URLDecoder.decode(strJson, "utf-8");//解析
		List<Goods> lstGoods = JSONObject.parseArray(strJson, Goods.class); 
		StringBuffer  sb = new StringBuffer();
		CommonVariable commonVariable = new CommonVariable();
		commonVariable.setCompanyId(companyId);
		commonVariable.setVarGroup("qrcodePrev");
		commonVariable.setVarName("qrcodePrevFlag");
		commonVariable=commonVariableService.findOne(commonVariable);
		String qrcodePrev="";
		if(commonVariable!=null&&commonVariable.getVarValue().equals("1")){
			qrcodePrev=variableService.getValByKey("QRCodePrev");
		}
		for(Goods  goods : lstGoods){
			sb.append(goods.getProduct().getMarketCode()+ ","+qrcodePrev +goods.getCode()+"\r\n");
		}
	    byte[] data = sb.toString().getBytes();  
	    fileName = URLEncoder.encode(fileName, "UTF-8");  
	    response.reset();  
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.addHeader("Content-Length", "" + data.length);  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);  
	    outputStream.flush();  
	    outputStream.close();  
	}
	
	/**
	 * 依据规格明细ID来查询商品信息;
	 * @param goods
	 * @return
	 */
	@RequestMapping("/findGoodsByStandardDetailId")
	@ResponseBody
	public List<Goods> findGoodsByStandardDetailId(Goods goods) {
		return goodsService.findGoodsByStandardDetailId(goods);
	}
	
	@RequestMapping(value="/findGoodsByStandardDetailIdLimit",method=RequestMethod.POST)
	@ResponseBody
	public List<Goods> findGoodsByStandardDetailIdLimit(Goods goods,int limit){
		return goodsService.findGoodsByStandardDetailIdLimit(goods,limit);
	}
	
	/**
	 * 获取配送类型;
	 * @param companyid
	 * @return
	 */
	@RequestMapping("/getdelivertype")
	@ResponseBody
	public List<Map<String, Object>> getDeliverType(String companyid) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 0);
		map.put("text", "默认类型");
		list.add(map);
		List<CommonVariable> cvs = commonVariableService.getDeliverType(companyid,"deliverType","1");
		for (CommonVariable cv : cvs) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", cv.getVarId());
			map1.put("text", cv.getVarValue());
			list.add(map1);
		}
		return list;
	}
	
	/**
	 * 获取查询条件的配送类型;
	 * @param companyid
	 * @return
	 */
	@RequestMapping("/getsearchdelivertype")
	@ResponseBody
	public List<Map<String, Object>> getSearchDeliverType(String companyid) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", "-1");
		map1.put("text", "全部");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", 0);
		map2.put("text", "默认类型");
		list.add(map2);
		List<CommonVariable> cvs = commonVariableService.getDeliverType(companyid,"deliverType","1");
		for (CommonVariable cv : cvs) {
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("id", cv.getVarId());
			map3.put("text", cv.getVarValue());
			list.add(map3);
		}
		return list;
	}
	
	/**获取商品批次号
	 * @return
	 */
	@RequestMapping(value="/getGoodsBatchNo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String getGoodsBatchNo(){
		return goodsService.getGoodsBatchNo();
	}
	
	@RequestMapping("/getGoodsLevels")
	@ResponseBody
	public List<CommonVariable> getGoodsLevels(){
		CommonVariable commonVariable = new CommonVariable();
		commonVariable.setVarGroup("goodsLevel");
		return commonVariableService.getCommonVariables(commonVariable);
	}
}
