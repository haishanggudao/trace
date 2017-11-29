package cn.rfidcer.util;

public class MyStringUtls {

	
	public static String subQRCode(String qrCode){
		if(qrCode.lastIndexOf("/")==-1){
			return qrCode;
		}
		return qrCode.substring(qrCode.lastIndexOf("/")+1);
		
	}
}
