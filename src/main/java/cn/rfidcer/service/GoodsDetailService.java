package cn.rfidcer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.UserToken;

/**   
* @Title: GoodsDetailService.java 
* @Package cn.rfidcer.service 
* @Description: Service 商品明细
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月4日 下午8:02:46 
* @version V1.0   
*/
public interface GoodsDetailService {
	
	/**根据商品二维码查找商品信息
	 * @param qrcode
	 * @return
	 */
	ResultMsg getGoodsByQRCode(String qrcode,UserToken userToken);
	

	/**新增商品明细
	 * @param detail
	 * @param qrNum
	 * @param currentUser
	 * @param multiImg 联排数量
	 * @return
	 */
	ArrayList<File> createOrUpdateGoodsDetail(GoodsDetail detail, int qrNum, User currentUser, int multiImg);



	/**
	 * @param companyId
	 * @param strCompanyCode
	 * @param goodsId
	 * @param currentUser
	 * @return
	 */
	GoodsDetail createNewGoodsDetail(String companyId, String strCompanyCode, String goodsId, User currentUser);
	
	/**
	 * 新增带有条形码的商品明细;
	 * @param companyId
	 * @param strCompanyCode
	 * @param goodsId
	 * @param currentUser
	 * @return
	 */
	GoodsDetail createNewGoodsDetailWithBarcode(String companyId, String strCompanyCode, String goodsId, String barcode, User currentUser);

	/**生成商品明细
	 * @param goods
	 * @param currentUser
	 * @return
	 */
	List<GoodsDetail> createGoodsDetail(Goods goods,User currentUser);
    
	List<GoodsDetail> createGoodsDetailAndUrl(Goods goods,User currentUser);
	
}
