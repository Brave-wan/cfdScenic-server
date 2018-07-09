package com.htrj.common.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.htrj.common.beanutils.BeanUtils;
import com.htrj.common.beanutils.PropertyUtils;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseDao<E, PK extends Serializable> extends SqlSessionDaoSupport {
	public final Logger	logger	= LoggerFactory.getLogger(getClass());

	@Autowired(required=false)
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Autowired(required=false)
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public E findById(PK id) {
		return getSqlSession().selectOne(getFindByPrimaryKeyStatement(), id);
	}

	public int deleteById(PK id) {
		int affectCount = getSqlSession().delete(getDeleteStatement(), id);
		return affectCount;
	}

	public int save(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSession().insert(getInsertStatement(), entity);
		return affectCount;
	}

	public int update(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSession().update(getUpdateStatement(), entity);
		return affectCount;
	}

	protected Page pageQuery(String statementName, PageRequest pageRequest) {
		return pageQuery(getSqlSession(), statementName, pageRequest);
	}
	protected Page pageQuery(SqlSession sqlSession, String statementName, PageRequest pageRequest) {

		// 查询参数
		Map filters = new HashMap();
		Map parameterObject = PropertyUtils.describe(pageRequest);
		filters.putAll(parameterObject);
		if (pageRequest.getFilters() instanceof Map) {
			filters.putAll((Map) pageRequest.getFilters());
		} else {
			parameterObject = BeanUtils.transBean2Map(pageRequest.getFilters());
			filters.putAll(parameterObject);
		}
		// 查询记录总数
		String countStatementName = getCountStatementForPaging(statementName);
		Number totalCount = (Number) sqlSession.selectOne(countStatementName, filters);
		if (totalCount == null || totalCount.longValue() <= 0) {
			return new Page(pageRequest, 0);
		}

		Page page = new Page(pageRequest, totalCount.intValue());

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		// filters.put("sortColumns", pageRequest.getSortColumns());
		List list = sqlSession.selectList(statementName, filters, new RowBounds(page.getFirstResult(), page.getPageSize()));
		page.setResult(list);
		return page;
	}


	/**
	 * 用于子类覆盖,在insert,update之前调用
	 * 
	 * @param entity
	 */
	protected void prepareObjectForSaveOrUpdate(E entity) {
	}

	protected String getMybatisMapperNamesapce(){
		return this.getClass().getName();
	}

	public String getFindByPrimaryKeyStatement() {
		return getMybatisMapperNamesapce() + ".getById";
	}

	public String getInsertStatement() {
		return getMybatisMapperNamesapce() + ".insert";
	}

	public String getUpdateStatement() {
		return getMybatisMapperNamesapce() + ".update";
	}

	public String getDeleteStatement() {
		return getMybatisMapperNamesapce() + ".delete";
	}

	public String getCountStatementForPaging(String statementName) {
		return statementName + "_count";
	}

	/** 组装mapper中映射的查询ID */
	public String getStatementName(String statementName) {
		return getMybatisMapperNamesapce() + "." + statementName;
	}
 
	public String getOutId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "");
		getSqlSession().selectOne(getStatementName("getOutId"), map);
		return map.get("id").toString();
	}

}
