package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.RecommendWay;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
@Repository
public class RecommendWayDao extends BaseDao<RecommendWay, Integer> {
	
    public int deleteByPrimaryKey(Long id){
    	return getSqlSession().delete(getStatementName("insertSelective"),id);
    }

    public int insertSelective(RecommendWay record){
    	return getSqlSession().insert(getStatementName("insertSelective"),record);
    }

    public int updateByPrimaryKeySelective(RecommendWay record){
    	return getSqlSession().update(getStatementName("updateByPrimaryKeySelective"),record);
    }

	/**
	 * @param pageRequest 
	* @Title: findGroupWay
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @author 张伟烁
	* @return List<Map<String,Object>>    返回类型
	* @throws
	*/ 
	public Page<Map<String,Object>> findGroupWay(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findGroupWay"),pageRequest);
	}

	/**
	* @Title: findWayByGroupId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param long1
	* @param @return    设定文件
	* @author 张伟烁
	* @return Object    返回类型
	* @throws
	*/ 
	public List<Map<String,Object>> findWayByGroupId(Long id) {
		return getSqlSession().selectList(getStatementName("findWayByGroupId"),id);
	}

	public void saveRecommendWay(RecommendWay rw) {
		getSqlSession().insert(getStatementName("insertSelective"),rw);
	}

	public Page getWayList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getWayList"),pageRequest);
	}

	public List<Map<String, Object>> getWayDetail(Long groupId) {
		return getSqlSession().selectList(getStatementName("getWayDetail"),groupId);
	}

	public void toDeleteWay(Long groupId) {
		getSqlSession().delete(getStatementName("toDeleteWay"),groupId);
	}

}