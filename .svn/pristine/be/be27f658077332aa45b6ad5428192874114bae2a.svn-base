package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.UserCommentDao;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class UserCommentService {
	@Autowired
	private UserCommentDao userCommentDao;

	public DataGrid scenicSpotComment(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = userCommentDao.scenicSpotComment(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	
	public List<Map<String,Object>> scenicComment(Map<String,Object> map) {
		return userCommentDao.scenicComment(map);
	}
	
	public List<UserComment> scenicUserIdComment(Map<String,Object> map) {
		return userCommentDao.scenicUserIdComment(map);
	}
	
	public List<Map<String,Object>> getCommentImg(Long commentId)
	{
		return userCommentDao.getCommentImg(commentId);
	}
	
}
