package cn.rfidcer.exception;

/**   
* @Title: DataErrorException.java 
* @Package cn.rfidcer.exception 
* @Description:自定义数据异常
* @author 席志明
* @date 2017年3月23日 上午11:41:15 
*/
public class DataErrorException extends RuntimeException{ 
	private static final long serialVersionUID = 8994658568541761342L;

	public DataErrorException(){
		super("数据异常");
	}
	
	public DataErrorException(String msg){
		super(msg);
	}
}
