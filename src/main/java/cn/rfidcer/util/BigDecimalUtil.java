package cn.rfidcer.util;

public class BigDecimalUtil {

	
	/**删除多余的0，如200.00变为200
	 * @param str
	 * @return
	 */
	public static String delLastZero(String str){
		if(str.indexOf(".")==-1){
			return str;
		}else if(str.endsWith("0")){
			return delLastZero(str.substring(0,str.length()-1));
		}else if(str.endsWith(".")){
			return str.substring(0,str.length()-1);
		}else{
			return str;
		}
	}
	
	public static void main(String[] args) {
		String a="20.00";
		System.out.println(delLastZero(a));
	}
}
