package com.podesta.ddu.bk.core.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;






//import com.hotent.core.mybatis.OffsetLimitInterceptor.BoundSqlSqlSource;
//import com.hotent.core.util.AppUtil;
import com.podesta.ddu.bk.core.db.myatis.Dialect;
import com.podesta.ddu.bk.util.AppUtil;
import com.podesta.ddu.bk.util.StringUtil;



/**
 * 为ibatis3提供基于方言(Dialect)的分页查询的插件
 * 将拦截Executor.query()方法实现分页方言的插入.
 * 配置文件内容:
 * 
 * <pre>
 *  &lt;plugins>
 *    &lt;plugin interceptor="package path.OffsetLimitInterceptor">
 *      &lt;property name="dialectClass" value="package path.dialect.MySQLDialect"/>
 *    &lt;/plugin>
 *  &lt;/plugins>
 * </pre>
 * @author badqiu
 */
@Intercepts(
{
	@Signature(
		type = Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
	)
})
public class OffsetLimitInterceptor implements Interceptor {

	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;
	private Dialect dialect;
	private Properties properties = null;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		getDialect();
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * 根据app.properties 的jdbc.dbType获取方言。
	 * @throws Exception
	 */
	private void getDialect() throws Exception
	{
		if(dialect!=null) return;
		Properties  prop=(Properties)AppUtil.getBean("configproperties");
		String dbType=prop.getProperty("jdbc.dbType");
		String dialectClass = this.properties.getProperty(dbType);
		try{
			dialect = (Dialect) Class.forName(dialectClass).newInstance();
		}
		catch (Exception e)
		{
			throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
		}
	}
	
	private void processIntercept(final Object[] queryArgs) throws Exception
	{
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();
		if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT))
		{
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim();
			if (dialect.supportsLimitOffset())
			{
				sql = dialect.getLimitString(sql, offset, limit);
				offset = RowBounds.NO_ROW_OFFSET;
			}
			else
			{
				sql = dialect.getLimitString(sql, 0, limit);
			}
			limit = RowBounds.NO_ROW_LIMIT;
			queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
			BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		}
	}

	// see: MapperBuilderAssistant
	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource)
	{
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		//不同的mybatis版本 用不同的方法getKeyProperties/getKeyProperty
		String[] properties=ms.getKeyProperties();
		String per=StringUtil.getArrayAsString(properties);
		builder.keyProperty(per);
		// setStatementTimeout()
		builder.timeout(ms.getTimeout());
		// setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		// setStatementResultMap()
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		// setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}
	
	public static class BoundSqlSqlSource implements SqlSource
	{
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql)
		{
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject)
		{
			return boundSql;
		}
	}
}
