package cn.rfidcer.enums;

/**   
  * @Title: 产品类型枚举
  * @author jgx
  * @date 2016年6月29日 上午9:39:07 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class ProductType {
	
	public enum Enums {
		 GOODS("商品", 1), MATERIAL("原料", 2);
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
	
/*	public static void main(String[] args) {
        for(ProductTypeEnum ptype : ProductTypeEnum.values()){
            System.out.println(ptype.getName(1));
        }
	}*/
 
}
