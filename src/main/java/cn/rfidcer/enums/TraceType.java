package cn.rfidcer.enums;

public enum TraceType {

	GOODS(0),//商品二维码
	GOODSDETAIL(1),//非团餐商品明细二维码
	GROUPDINNER(2),//团餐二维码
	SALEGOODS(3);//销售单商品二维码
	
	private final int value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	TraceType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
