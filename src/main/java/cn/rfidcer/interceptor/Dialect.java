package cn.rfidcer.interceptor;

/**分页
 * @author xzm
 *
 */
public interface Dialect {

	/**
	 * @param sql 查询sql
	 * @param offset 偏移量
	 * @param limit 条数
	 * @return
	 */
	String getLimitSql(String sql, int offset, int limit);
	
	String getCountSql(String sql);

}