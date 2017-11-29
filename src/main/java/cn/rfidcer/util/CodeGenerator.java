package cn.rfidcer.util;

import org.apache.commons.lang3.RandomUtils;

/**
 * 生成 编码的工具类
 */
public class CodeGenerator {
	
	/**
	 * 企业编码 9位数字国标，地区编码+3位流水号（企业流水码） 310109+001
	 * @param areaCode
	 * @return
	 */
	public static String getCompanyCode(String areaCode) {
		return areaCode + String.valueOf(RandomUtils.nextInt(100, 999));
	}
	
}