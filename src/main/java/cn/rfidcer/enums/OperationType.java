package cn.rfidcer.enums;

/**   
  * @Title: 库存操作枚举类
  * @author jgx
  * @date 2016年6月24日 下午3:14:22 
  * @Copyright Copyright
  * @version V1.0   
*/
public enum OperationType {

	SALERETURN("s"),//退货
	INSTOCK("i"),//进货
	OUTSTOCK("o"),//出货
	COMPLEMENT("c"),//补货
	LOSS("l"),//损耗
	UPDATE("u");//更新
	private final String value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	OperationType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
}
