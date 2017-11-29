package cn.rfidcer.service;

/**   
* @Title: TraceCodeService.java 
* @Description:追溯码
* @author 席志明
* @date 2016年8月4日 下午3:40:39 
* @version V1.0   
*/
public interface TraceCodeService {

	/**生成追溯码
	 * 企业编码9位+6位日期+5位流水 310109001+160330+00001
	 * @param companyCode 企业编码9位
	 * @return
	 */
	String newTraceCode(String companyId,String companyCode);

	String newTraceCode(String companyId);
}
