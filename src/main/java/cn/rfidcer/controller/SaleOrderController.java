package cn.rfidcer.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.svenson.JSONParser;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.OutstockService;
import cn.rfidcer.service.SaleOrderService;
import cn.rfidcer.util.UUIDGenerator;
 
/**
* @Title: SaleOrderController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller  销售单信息
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午10:54:16 
* @version V1.0
 */
@Controller
@RequestMapping("/sale_order")
public class SaleOrderController {
	
	@Autowired
	private SaleOrderService saleOrderService;
	@Autowired
	private OutstockService outstockservice;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "saleOrder";
	} 
	
	/**
	 * 获取所有的销售单记录列表;
	 * @param page
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<SaleOrder> list(Page page,SaleOrder saleOrder){ 
		return saleOrderService.findAll(page, saleOrder);
	}
	
	/**
	 * 根据订单状态查询销售单列表
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping("/findByStatus")
	@ResponseBody
	public List<SaleOrder> findByStatus(SaleOrder saleOrder){ 
		return saleOrderService.findByStatus(saleOrder);
	}
	
	/**
	 * 获取所有的销售单记录列表;
	 * @param page
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"trans:sale_order:list"})
	public Map<String, Object> findAllList(Page page,SaleOrder saleOrder){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<SaleOrder> list = saleOrderService.findAll(page, saleOrder);
		map.put("rows", list);		
		return map;
	}
	
	/**
	 * 获取出库明细记录;
	 * @param page
	 * @param saleItem
	 * @return
	 */
	@RequestMapping("/findAllOutstockItems")
	@ResponseBody
	public Map<String, Object> findAllOutstockItems(Page page, SaleItem saleItem){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", saleOrderService.findAllOutstockItems(page, saleItem));		
		return map;
	}
	
	/**
	 * 获取销售单明细记录;
	 * @param page
	 * @param saleItem
	 * @return
	 */
	@RequestMapping("/findAllItems")
	@ResponseBody
	public Map<String, Object> findAllItems(Page page, SaleItem saleItem){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", saleOrderService.findAllItems(page, saleItem));		
		return map;
	}
	
	@RequestMapping(value="/getAllItems",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<SaleItem> getAllItems(SaleItem saleItem){
		return saleOrderService.findAllItems(null, saleItem);		
	}
	/**
	 * 新增或者修改销售单
	 * @param saleOrder
	 * @param currentUser
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"trans:sale_order:add","trans:sale_order:edit"},logical=Logical.OR)
	public ResultMsg addOrUpdate(SaleOrder saleOrder, @CurrentUser User currentUser,String saleItemList){
		return saleOrderService.addOrUpdate(saleOrder, currentUser,saleItemList);
	}
	/**
	 * 新增或者修改销售单；created by jie.jia at 2016-01-11 09:51
	 * @param saleOrder
	 * @param currentUser
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addSaleOrder",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"trans:sale_order:add","trans:sale_order:edit"},logical=Logical.OR)
	public ResultMsg addProcess(SaleOrder saleOrder, @CurrentUser User currentUser, HttpServletRequest request){
		String json = request.getParameter("saleItemList");
		JSONParser parser = new JSONParser(); 
		@SuppressWarnings("rawtypes")
		ArrayList list = parser.parse(ArrayList.class, json);
		List<SaleItem> result = new ArrayList<SaleItem>();
		for(int i = 0 ; i < list.size() ; i++){ 
			@SuppressWarnings({ "rawtypes", "unchecked" })
			HashMap<String, String> map = (HashMap) list.get(i);
			SaleItem mySaleItem = new SaleItem();
			mySaleItem.setSaleItemId( UUIDGenerator.generatorUUID() );
			mySaleItem.setProductId( map.get("productId"));
			BigDecimal myQty = new BigDecimal( String.valueOf(map.get("quantity")));
			mySaleItem.setQuantity( myQty );
			mySaleItem.setProductStandardDetailId( map.get("productStandardDetailId"));
			result.add(mySaleItem);
		}
		saleOrder.setSaleItems(result);
		return saleOrderService.createOrUpdateSaleOrder(saleOrder, currentUser);
	}
	
	/**
	 * 删除销售单；created by jie.jia at 2016-01-11 09:51
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping(value="/delSaleOrder",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"trans:sale_order:delete"})
	public ResultMsg delSaleOrder(SaleOrder saleOrder){ 
		return saleOrderService.delSaleOrder(saleOrder);
	}
	
	/**
	 * 删除销售单明细
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping(value="/delSaleOrderItems",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"trans:sale_order:delete"})
	public ResultMsg delSaleOrderItems(SaleItem saleItem){ 
		return saleOrderService.delSaleOrderItems(saleItem);
	}
	
	
	
	/**
	 * 下载销售单二维码
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping(value="/downloadqc")
	@RequiresPermissions(value={"trans:sale_order:downQRCode","trans:saleorderoutstockmain:qrCode","trans:groupDinnerOutStock:qrCode"},logical=Logical.OR)
	public void downloadqc(String saleorderid,HttpServletRequest request,
	        HttpServletResponse response){
		try {
			response.setContentType("Content-type: text/zip");
			response.setHeader("Content-Disposition", "attachment; filename=mytest.zip");
			String[] ids = saleorderid.split(",");
			System.out.println("saleorderid is " + saleorderid);
			// List of files to be downloaded
			List<File> files = new ArrayList<File>();
			// 51 px = 18mm
			for (String id : ids) {
				List<OutstockMain> oms = outstockservice.listOutstockMainBySaleOrderId(id);
				if(oms != null && !oms.isEmpty()) {
					OutstockMain outstockMain = oms.get(0);
				File file = QRCode.from(outstockMain.getOutstockMainId()).to(ImageType.JPG).withSize(71, 71).file();
				BufferedImage ImageTwo = ImageIO.read(file);
				int width = ImageTwo.getWidth();// 图片宽度
				int height = ImageTwo.getHeight();// 图片高度
				int realwidth = 142;
				int width2 = realwidth - width;
				BufferedImage ImageOne = new BufferedImage(width2, height, BufferedImage.TYPE_4BYTE_ABGR);
				Graphics g = ImageOne.getGraphics();// 
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, width * 2, height);
				g.setColor(Color.black);
				Font mFont = new Font("宋体", Font.PLAIN, 12);
				g.setFont(mFont);
				String text1 = "";
				String text2 = "质尊溯源";
				String text3 = "";
				int d = (int) (width2 * 0.05);
				int e1 = (int) (height * 0.3);
				int e2 = (int) (height * 0.6);
				int e3 = (int) (height * 0.9);
				g.drawString(text1, d, e1);
				g.drawString(text2, d, e2);
				g.drawString(text3, d, e3);
				// 从图片中读取RGB
				int[] ImageArrayOne = new int[width2 * height];
				ImageArrayOne = ImageOne.getRGB(0, 0, width2, height, ImageArrayOne, 0, width2);

				int[] ImageArrayTwo = new int[width * height];
				ImageArrayTwo = ImageTwo.getRGB(0, 0, width, height, ImageArrayTwo, 0, width);
				// 生成新图片
				BufferedImage ImageNew = new BufferedImage(realwidth, height, BufferedImage.TYPE_INT_RGB);
				ImageNew.setRGB(width, 0, width2, height, ImageArrayOne, 0, width2);// 设置右半部分的RGB
				ImageNew.setRGB(0, 0, width, height, ImageArrayTwo, 0, width);// 设置左半部分的RGB
				ImageIO.write(ImageNew, "jpg", file);
				files.add(file);
				}
			}
			ServletOutputStream out = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));
			for (File file : files) {
				System.out.println("Adding " + file.getName());
				zos.putNextEntry(new ZipEntry(file.getName()));
				// Get the file
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);

				} catch (FileNotFoundException fnfe) {
					// If the file does not exists, write an error entry instead of
					// file
					// contents
					zos.write(("ERRORld not find file " + file.getName()).getBytes());
					zos.closeEntry();
					System.out.println("Couldfind file " + file.getAbsolutePath());
					continue;
				}
				BufferedInputStream fif = new BufferedInputStream(fis);
				// Write the contents of the file
				int data = 0;
				while ((data = fif.read()) != -1) {
					zos.write(data);
				}
				fif.close();
				zos.closeEntry();
				System.out.println("Finishedng file " + file.getName());
			}
			zos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getSaleOrderNo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String getSaleOrderNo(){
		return saleOrderService.getSaleOrderNo();
	}
}
