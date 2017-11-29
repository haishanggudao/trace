package cn.rfidcer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 批次号的Util
 * @author jie.jia created by jie.jia at 2016-01-04 17:30
 */
public class BatchNumberUtil {
	
	/**
	 * 获取商品批次号；created by jie.jia at 2016-01-04 17:33
	 * @return
	 */
	public static String getGoodsBatch(){
		String myGoodsBatch = "";
		String myPattern = "yyyy-MM-dd";
		
		Date date = new Date();
		myGoodsBatch = DateUtil.format(date, myPattern).replaceAll("-", "");
		
		return myGoodsBatch;
	}
	public static String getStockBatch(){
		return new SimpleDateFormat("yyyyMMddHHmmSSS").format(new Date());
	}
	
	

}
