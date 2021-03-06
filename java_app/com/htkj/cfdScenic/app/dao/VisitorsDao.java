package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.Visitors;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VisitorsDao  extends BaseDao<Visitors, Integer>{

	public List<Map<String, String>> scenicSpotList() {
		return getSqlSession().selectList(getStatementName("scenicSpotList"));
	}

	public Map<String, Object> scenicSpotParticulars(Long id) {
		return getSqlSession().selectOne(getStatementName("scenicSpotParticulars"),id);
	}

	public List<Map<String, String>> obscureSelect(String name) {
		return getSqlSession().selectList(getStatementName("obscureSelect"),name);
	}

	public List<Map<String, String>> selectMessage(String name) {
		return getSqlSession().selectList(getStatementName("selectMessage"),name);
	}

	public List<Map<String, String>> tagsVisitors() {
		return getSqlSession().selectList(getStatementName("tagsVisitors"));
	}
	public List<Map<String, String>> indexVisitors() {
		return getSqlSession().selectList(getStatementName("indexVisitors"));
	}

	public List<Map<String,Object>> scenicSpotLimitPage() {
		return getSqlSession().selectList(getStatementName("scenicSpotLimitPage"));
	}

	public List getIntegralGoods() {
		return getSqlSession().selectList(getStatementName("getIntegralGoods"));
	}

	public Map getIntegralGoodsDetaili(Long id) {
		return getSqlSession().selectOne(getStatementName("getIntegralGoodsDetaili"),id);
	}

	public Page shopListLimit(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("shopListLimit"), pageRequest);
	}

	public Map<String, String> oneShopMessage(Long id) {
		return getSqlSession().selectOne(getStatementName("oneShopMessage"),id);
	}

	public Page getActivity(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getActivity"), pageRequest);
	}

	public Map getActivityDetail(Long id) {
		return getSqlSession().selectOne(getStatementName("getActivityDetail"),id);
	}

	/**
	 * Title:景点-添加
	 * @author wangfenglong
	 * @date 2016年9月2日
	 */
	public int insert(Visitors visitors) {
		int i = 0;
		i = getSqlSession().insert(getStatementName("insertSelective"), visitors);
		return i;
	}

	public int updateByPrimaryKeySelective(Visitors visitors) {
		int i = 0;
		i = getSqlSession().update(getStatementName("updateByPrimaryKeySelective"), visitors);
		return i;
	}

	public Page pageGetVisitors(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pageGetVisitorsList"), pageRequest);
	}

	public int deleteByPrimaryKey(Long id) {
		int i = 0;
		i = getSqlSession().insert(getStatementName("deleteByPrimaryKey"), id);
		return i;
	}

	public Visitors selectByPrimanrKey(Long id) {
		return getSqlSession().selectOne(getStatementName("selectByPrimanrKey"), id);
	}

	public List<Map<String, Object>> findAllVisitors() {
		return getSqlSession().selectList(getStatementName("findAllVisitors"));
	}

	public Page findVisitorsList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findVisitorsList"), pageRequest);
	}

	public List<Map<String, Object>> getVisitorsInfo() {
		return getSqlSession().selectList(getStatementName("getVisitorsInfo"));
	}

}
