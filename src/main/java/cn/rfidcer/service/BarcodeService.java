package cn.rfidcer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.User;
import net.lingala.zip4j.core.ZipFile;

/**
* @Title: BarcodeService.java 
* @Package cn.rfidcer.service 
* @Description: Service Barcode 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月23日 下午7:25:25 
* @version V1.0
 */
public interface BarcodeService {
	
	/**
	 * 生成条形码文件；created by jie.jia at 2016-06-23 19:30
	 * @param content
	 * @return
	 * @throws Exception 
	 */
	File executeBarcodeFile( String content) throws Exception;
	
	String executeBarcodeString(String companyId, String goodsId);
	
	/**
	 * 更新商品明细；created by jie.jia at 2016-06-23 20:02
	 * @param detail
	 * @param qrNum
	 * @param currentUser
	 * @return
	 */
	ArrayList<File> createOrUpdateGoodsDetail(GoodsDetail detail, int qrNum, User currentUser );
	
	/**
	 * get the file of zip; created by jie.jia at 2016-06-23 20:15
	 * @param list
	 * @return
	 */
	ZipFile getZipFile(ArrayList<File> list, GoodsDetail detail);
	
	/**根据商品数量生成商品明细
	 * @param detail
	 * @param qrNum
	 * @param currentUser
	 * @return
	 */
	List<GoodsDetail> createDetailList(GoodsDetail detail, int qrNum, User currentUser);
}
