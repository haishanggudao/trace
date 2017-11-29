package cn.rfidcer.service;

import javax.servlet.http.HttpServletRequest;

/**   
* @Title: IPService.java 
* @Package cn.rfidcer.service 
* @Description:收集追溯访问
* @author 席志明
* @Copyright Copyright
* @date 2016年8月11日 下午2:51:34 
* @version V1.0   
*/
public interface IPService {

	/**发送二维码访问信息至MQ
	 * @param qrCode
	 * @param request
	 */
	void sendIPInfoToMQ(String qrCode,HttpServletRequest request);
}
