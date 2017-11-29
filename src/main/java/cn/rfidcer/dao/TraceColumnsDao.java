package cn.rfidcer.dao;

import cn.rfidcer.enums.TraceInfoType;

/**追溯字段
 * @author xzm
 *
 */
public interface TraceColumnsDao {

	String getTraceColumnsByCompanyCodeAndType(String companyCode,TraceInfoType traceType);
	
}
