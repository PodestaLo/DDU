package com.podesta.ddu.bk.core.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.podesta.ddu.bk.core.page.PageBean;
import com.podesta.ddu.bk.core.web.QueryFilter;

/**
 * DAO接口。<br>
 * 默认支持添加，删除，更新，查询，分页方法。
 * @author hotent
 */
public interface IEntityDao<E, PK extends Serializable>
{
	/**
	 * 添加对象
	 * @param entity
	 * @return
	 */
	public void add(E entity);

	/**
	 * 根据主键删除
	 * @param id
	 */
	public int delById(PK id);

	/**
	 * 更新对象
	 * @param entity
	 */
	public int update(E entity);

	/**
	 * 根据主键ID取得对象
	 * @param id
	 * @return
	 */
	public Object getById(PK id);

	/**
	 * 根据条件获取列表数据
	 * @param statementName
	 * @param params
	 * @return
	 */
	public List<E> getList(String statementName,Object params);
	
	
	/**
	 * 按某一SQL取得分页的数据列表
	 * @return
	 */
	public List<E> getList(String statementName,Object params,PageBean pb);
	
	/**
	 * 获取该表的所有记录
	 * @return
	 */
	public List<E> getAll();
	
	/**
	 * 按过滤器查询记录列表
	 * @param queryFilter
	 * @return
	 */
	public List<E> getAll(QueryFilter queryFilter);
	
	// pengyx
	public List<E> getAllIn(QueryFilter queryFilter);
	//按条件查询不分页
	public List<E> nopagegetList(QueryFilter queryFilter);
	/**
	 * 返回某一条单独的数据
	 * @param sqlKey map xml 中的语句Id
	 * @param params
	 * @return
	 */
	public E getUnique(String sqlKey,Object params);
	
	/**
	 * added by pengyx:
	 * 按map过滤查询记录列表
	 * @param Map filters
	 * @return
	 */
	public List<E> findByMapFilter(Map<String, Object> filters);

	/**
	 * added by pengyx:
	 * 按map和排序方式过滤查询记录列表
	 * @param Map filters
	 * @param String orderField
	 * @param String orderSeq
	 * @return
	 */
	public List<E> findByMapFilter(Map<String, Object> filters, String orderField, String orderSeq);
	
	
	/**
	 * add by lukun 2013-05-21
	 * 按某一SQL取得分页的数据列表
	 * @return
	 */
	public List<?> getQueryList(String sqlKey,Object params,PageBean pb);
	
	/**
	 * add by lukun 2013-05-21
	 * 根据条件获取列表数据
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public List<?> getQueryList(String sqlKey,Object params);
	
	
}
