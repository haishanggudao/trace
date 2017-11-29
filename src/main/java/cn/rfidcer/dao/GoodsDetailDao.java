package cn.rfidcer.dao;

import java.util.List;

import cn.rfidcer.bean.GoodsDetail;
 
/**   
* @Title: GoodsDetailDao.java 
* @Package cn.rfidcer.dao 
* @Description: 商品明细
* @author xzm
* @Copyright Copyright
* @date 2016年7月4日 下午7:49:25 
* @version V1.0   
*/
public interface GoodsDetailDao {

	
	/**根据商品二维码查找商品信息
	 * @param qrcode
	 * @return
	 */
	GoodsDetail getGoodsByQRCode(String qrcode);
	
	int addOrUpdateGoodsDetail(GoodsDetail detail);
	/**根据商品条形码查找商品信息
	 * @param qrcode
	 * @return
	 */
	GoodsDetail getGoodsByBarCode(String barCode);
	/**
	 * 新增带有条形码的商品明细; added by jie.jia at 2016-07-04 20:07
	 * @param detail
	 * @return
	 */
	int addGoodsDetailWithBarcode(GoodsDetail detail);
	
	/**
	 * 修改商品明细; added by jie.jia at 2016-07-04 19:49
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(GoodsDetail record);
	
	/**根据商品ID查询已关联的商品明细
	 * @param GoodsId
	 * @return
	 */
	List<GoodsDetail> getGoodsDetailsByGoodsId(String goodsId);
}
