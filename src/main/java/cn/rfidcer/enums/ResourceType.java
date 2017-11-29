package cn.rfidcer.enums;

/**   
  * @Title: 资源类型枚举类
  * @author zxm
  * @date 2016年6月24日 下午3:14:22 
  * @Copyright Copyright
  * @version V1.0   
*/
public enum ResourceType {

	menu("菜单"), button("按钮");
	private final String value;
	
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ResourceType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
}
