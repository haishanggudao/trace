package cn.rfidcer.interceptor;

/**
 * Mysql分页
 * 
 * @author xzm
 * 
 */
public class DialectMysqlImpl implements Dialect {


	@Override
	public String getLimitSql(String sql, int offset, int limit) {
		StringBuilder newsql = new StringBuilder(sql);
		newsql.append(" LIMIT " + offset + "," + limit);
		return newsql.toString();
	}

	@Override
	public String getCountSql(String sql) {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(0) from (");
		sb.append(sql);
		sb.append(") c");
		return sb.toString();
	}
}
