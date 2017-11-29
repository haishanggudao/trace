package cn.rfidcer.enums;


/**   
 * @Title: StroeSalePayType.java 
 * @Package cn.rfidcer 
 * @Description: 门店支付类型
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月11日 下午3:15:23 
 * @version V1.0   
*/
public class StroeSalePayType {
	
	public enum Enums {
		CASH("现金", 0), ALIPAY("支付宝", 1), WECHAT("微信", 2), BANKCARD("银行卡", 3), CLUBCARD("会员卡", 4),OTHER("其他", 5);
		// 成员变量
		private String name;
		private int index;
		// 构造方法
		private Enums(String name, int index) {
			this.name = name;
			this.index = index;
		}
		// 普通方法
		public static String getName(int index) {
			for (Enums c :Enums.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}
		// get set 方法
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	private String productTypeName;
	private String productType;
	
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
 
}
