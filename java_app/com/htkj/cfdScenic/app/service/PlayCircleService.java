package com.htkj.cfdScenic.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.AdvertisementDao;
import com.htkj.cfdScenic.app.dao.CommentSumDao;
import com.htkj.cfdScenic.app.dao.PictureLibraryDao;
import com.htkj.cfdScenic.app.dao.TravelogsDao;
import com.htkj.cfdScenic.app.dao.UserCommentDao;
import com.htkj.cfdScenic.app.dao.VisitorsDao;
import com.htkj.cfdScenic.app.dao.VisitorsOrderDao;
import com.htkj.cfdScenic.app.model.CommentSum;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.Travelogs;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htrj.common.base.BaseService;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class PlayCircleService extends BaseService{

	@Autowired
	private TravelogsDao travelogsDao;
	@Autowired
	private AdvertisementDao advertisementDao;
	@Autowired
	private VisitorsDao visitorsDao;
	@Autowired
	private VisitorsOrderDao visitorsOrderDao;
	@Autowired
	private CommentSumDao commentSumDao;
	@Autowired
	private UserCommentDao userCommentDao;
	@Autowired
	private PictureLibraryDao pictureLibraryDao;
	
	
	
	
	/**
	 *	游乐圈首页获取游记
	 */
	public DataGrid getTraveLogs(PageRequest<Map<String, Object>> pageRequest) {
		Page page = travelogsDao.getTraveLogs(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}

	/**
	 *	获取轮播图 
	 */
	public List getImg() {
		return advertisementDao.getImg();
	}
	/**
	 *	获取活动，或者结伴游活动 
	 */
	public DataGrid getActivity(PageRequest<Map<String, Object>> pageRequest) {
		Page page = visitorsDao.getActivity(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}
	/**
	 * 	获取活动详情
	 */
	public Map getActivityDetail(Long id) {
		return visitorsDao.getActivityDetail(id);
	}
	/**
	 * 	获取参加的人数
	 */
	public Integer getPersonCount(Map para) {
		return visitorsOrderDao.getPersonCount(para);
	}
	/**
	 * 	插入一条分享数据
	 */
	public void saveShare(CommentSum commentSum) {
		commentSumDao.saveShare(commentSum);
	}
	/**
	 * 	删除赞记录
	 */
	public void deleteFavor(CommentSum commentSum) {
		commentSumDao.deleteFavor(commentSum);
	}
	
	
	/**	
	 * 	获取精彩游记
	 * @param pageRequest 
	 */
	public DataGrid getWonderful(PageRequest<Map<String, Object>> pageRequest) {
		Page page = travelogsDao.getWonderful(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}
	/**
	 * 获取赞,评论,分享的总数
	 */
	public int getTraveLogsCount(String id,int type) {
		int i = 0;
		switch (type) {
		case 1:
			i = commentSumDao.getTraveLogsCount(id);
			break;
		case 2:
			i = commentSumDao.getCommentCount(id);
			break;
		case 3:
			i = commentSumDao.getshareCount(id);
			break;
		}
		return i;
	}

	/**
	 * 获取是否赞过
	 * @param map
	 * @return
	 */
	
	public Integer getFavorUserIdCount(Map<String,Object> map) {
		return commentSumDao.getFavorUserIdCount(map);
	}
	
	public List getCommentById(Map map) {
		return travelogsDao.getCommentById(map);
	}
	
	/**	
	 * 	写精彩游记
	 * 
	 */
	public void writeCollect(Travelogs travelogs) {
		travelogsDao.writeCollect(travelogs);
	}
	
	/**	
	 * 	添加游记评论
	 * 
	 */
	public void writeComment(UserComment userComment) {
		userCommentDao.wtiteComment(userComment);
	}

	public List<String> getPicByTravelId(Long travelId) {
		return pictureLibraryDao.getPicByTravelId(travelId);
	}

	public void savePictureLibrary(PictureLibrary pictureLibrary) {
		pictureLibraryDao.savePictureLibraryAll(pictureLibrary);
	}

	public DataGrid getTravelsByUserId(PageRequest<Map<String, Object>> pageRequest) {
		Page page = travelogsDao.getTravelsByUserId(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}

	public DataGrid getCommentTravelsByUserId(PageRequest<Map<String, Object>> pageRequest) {
		Page page = travelogsDao.getCommentTravelsByUserId(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}

	public List<String> getPicByUserCommentId(Long userCommentId) {
		return pictureLibraryDao.getPicByUserCommentId(userCommentId);
	}

	public Map<String,Object> checkTravelLogDetail(Long travelLogId) {
		return travelogsDao.checkTravelLogDetail(travelLogId);
	}

	public void deleteMyTravelLog(Long id) {
		travelogsDao.deleteMyTravelLog(id);
	}
	
	
}
