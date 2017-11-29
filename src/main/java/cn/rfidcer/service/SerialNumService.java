package cn.rfidcer.service;

import cn.rfidcer.bean.CommonVariable;

public interface SerialNumService {

	/**新建流水号,建议条件使用companyId、varGroup、varName来确定，可屏蔽因正式环境varID可能不同带来的麻烦
	 * @param commonVariable 公共变量
	 * <p>varValue 流水号最大值，超过后会重新归1<p>
	 * <p>varId 变量ID</p>
	 * <p>varGroup 变量组</p>
	 * <p>varName 变量名称</p>
	 * <p>companyId 企业ID</p>
	 * @return
	 */
	public String newSerialNum(CommonVariable commonVariable);
}
