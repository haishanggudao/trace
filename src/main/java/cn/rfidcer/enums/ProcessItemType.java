package cn.rfidcer.enums;

/**   
  * @Title: 加工类型枚举
  * @author jgx
  * @date 2016年6月29日 上午9:39:22 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class ProcessItemType {
	
	public enum Enums {
		 FABRIC("主料", 0), ACCESSORY("辅料", 1);
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
	
	private String typeName;
	private String type;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
/*	public static void main(String[] args) {
        for(ProductTypeEnum ptype : ProductTypeEnum.values()){
            System.out.println(ptype.getName(1));
        }
	}*/
 
}
