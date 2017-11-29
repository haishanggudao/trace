package cn.rfidcer.bean;

/**   
  * @Title: 商品二维码
  * @author xzm
  * @date 2016年6月27日 上午10:16:07 
  * @Copyright Copyright
  * @version V1.0   
*/
public class GoodQRCode {
	/**#商品二维码 */
	private String goodQRCodeId;

	/**
	 * 获取 #商品二维码
	 * @return goodQRCodeId
	 */
	public String getGoodQRCodeId() {
		return goodQRCodeId;
	}

	/**
	 * 设置 #商品二维码
	 * @param goodQRCodeId #商品二维码
	 */
	public void setGoodQRCodeId(String goodQRCodeId) {
		this.goodQRCodeId = goodQRCodeId;
	}

	@Override
	public String toString() {
		return "GoodQRCode [goodQRCodeId=" + goodQRCodeId + "]";
	}
	
	
	
}
