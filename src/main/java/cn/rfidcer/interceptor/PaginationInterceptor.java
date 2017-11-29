package cn.rfidcer.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * mybatis分页拦截器，分页对象为Mybatis的RowBounds对象
 * 
 * @author xzm
 *
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class}) })
public class PaginationInterceptor implements Interceptor {


	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		
//		RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");
//		/*
//		 * 无分页参数，直接跳过分页拦截器
//		 */
//		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
//			return invocation.proceed();
//		}
		BoundSql boundSql = statementHandler.getBoundSql();
		Object params = boundSql.getParameterObject();
		if(params instanceof Map){
			@SuppressWarnings("unchecked")
			Map<Object,Object> map=(Map<Object, Object>) params;
			Collection<Object> values = map.values();
			for (Object object : values) {
				if(object instanceof Page){
					preparePage(invocation, statementHandler, object);
					break;
				}
			}
		}else if(params instanceof Page){
			preparePage(invocation, statementHandler, params);
		}
		return invocation.proceed();
	}

	private void preparePage(Invocation invocation, StatementHandler statementHandler, Object object)
			throws SQLException {
		ParameterHandler parameterHandler = statementHandler.getParameterHandler();
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		Dialect dialect = new DialectMysqlImpl();
		String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");
		Connection connection=(Connection) invocation.getArgs()[0];
		PreparedStatement preStatement = connection.prepareStatement(dialect.getCountSql(originalSql));
		parameterHandler.setParameters(preStatement);
		ResultSet rs = preStatement.executeQuery();
		Page page=	(Page) object;
		if(rs.next()){
			page.setTotalNum(rs.getInt(1));
		}
		if(page.getRows()!=-1 && !page.isEmpty()){
			metaObject.setValue("delegate.boundSql.sql", dialect.getLimitSql(originalSql, page.getOffset(), page.getRows()));
		}
//		metaObject.setValue("delegate.rowBounds.offset",
//				RowBounds.NO_ROW_OFFSET);
//		metaObject.setValue("delegate.rowBounds.limit",
//				RowBounds.NO_ROW_LIMIT);
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
