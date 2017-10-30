package com.podesta.ddu.bk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.podesta.ddu.bk.core.db.IEntityDao;
import com.podesta.ddu.bk.core.page.PageBean;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.podesta.ddu.bk.core.web.QueryFilter;
import com.podesta.ddu.bk.util.BeanUtils;

/**
 * 服务基类。
 * 所有的业务实现类均需要从此类继承
 * @author csx
 *
 */
public abstract class GenericService <E, PK extends Serializable>{
//	protected Logger log = LoggerFactory.getLogger(GenericService.class);
	/**
	 * 需要被子类覆盖
	 * @return
	 */
	protected abstract IEntityDao<E,PK> getEntityDao();

	/**
	 * 添加对象
	 * @param entity
	 */
	public void add(E entity)
	{
		getEntityDao().add(entity);
	}

	/**
	 * 根据主键删除对象
	 * @param id
	 */
	public void delById(PK id)
	{
		getEntityDao().delById(id);
	}

	/**
	 * 根据主键批量删除对象
	 * @param ids
	 */
	public void delByIds(PK[] ids){
		if(BeanUtils.isEmpty(ids)) return;
		for (PK p : ids){
			delById(p);
		}
	}

	/**
	 * 修改对象
	 * @param entity
	 */
	public void update(E entity)
	{
		getEntityDao().update(entity);
	}

	/**
	 * 根据主键Id获取对象
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E getById(PK id)
	{
		return (E) getEntityDao().getById(id);
	}

	/**
	 * 取得分页。
	 * @param statatementName
	 * @param pb
	 * @return
	 */
	public List<E> getList(String statatementName,PageBean pb)
	{
		List<E>  list = getEntityDao().getList(statatementName, pb);
		return list;
	}
	/**
	 * 返回所有记录
	 * @return
	 */
	public List<E> getAll()
	{
		return getEntityDao().getAll();
	}
	
	//pengyx
	public List<E> getAllIn(QueryFilter queryFilter) {
		return getEntityDao().getAllIn(queryFilter);
	}
	//按条件查询不分页
	public List<E> nopageAll(QueryFilter queryFilter) {
		return getEntityDao().nopagegetList(queryFilter);
	}
	/**
	 * 按过滤器查询记录列表
	 * @param queryFilter
	 * @return
	 */
	public List<E> getAll(QueryFilter queryFilter){
		return getEntityDao().getAll(queryFilter);
	}
	
	/**
	 * 2013-05-18 pengyx: 新增 按map过滤查询记录列表
	 * @param Map filters
	 * @return
	 */
	public List<E> findByMapFilter(Map<String, Object> filters)
	{
		return getEntityDao().findByMapFilter(filters);
	}

	/**
	 * 2013-05-18 pengyx: 新增 按map和排序方式过滤查询记录列表
	 * @param Map filters
	 * @param String orderField
	 * @param String orderSeq
	 * @return
	 */
	public List<E> findByMapFilter(Map<String, Object> filters, String orderField, String orderSeq)
	{
		return getEntityDao().findByMapFilter(filters, orderField, orderSeq);
	}
}
