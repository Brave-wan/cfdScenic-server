package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.UserComment;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class UserCommentDao  extends BaseDao<UserComment, Integer>{

	public Page scenicSpotComment(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("scenicSpotComment"), pageRequest);
	}
	
	public  List<Map<String,Object>> scenicComment(Map<String,Object> map) {
		return getSqlSession().selectList(getStatementName("scenicSpotComment"),map);
	}
	
	public  List<UserComment> scenicUserIdComment(Map<String,Object> map) {
		return getSqlSession().selectList(getStatementName("scenicUserIdComment"),map);
	}

	public void saveUserComment(UserComment userComment) {
		getSqlSession().insert(getStatementName("saveUserComment"),userComment);
	}
	
	public List<Map<String,Object>> getCommentImg(Long commentId)
	{
		return getSqlSession().selectList(getStatementName("getCommentImg"),commentId);
	}
	
	
	public void wtiteComment(UserComment userComment)
	{
		getSqlSession().insert(getStatementName("saveUserComment"),userComment);
	}

	public void saveVisitorsUserComment(UserComment userComment) {
		getSqlSession().insert(getStatementName("saveVisitorsUserComment"),userComment);
	}

}
