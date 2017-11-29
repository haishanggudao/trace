package cn.rfidcer.enums;

/**   
  * @Title: 库存变化枚举类
  * @author jgx
  * @date 2016年6月24日 下午3:15:05 
  * @Copyright Copyright
  * @version V1.0   
*/
public enum ChangeType {

	ADD("1"),//增加
	SUBTRACT("-1"),//减少
	UNKNOWN("0");//未知
	
	private final String value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ChangeType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
}
