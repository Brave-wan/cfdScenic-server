package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.NewsNoticeDao;
import com.htkj.cfdScenic.app.model.NewsNotice;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class NewsNoticeService {
	@Autowired
	private NewsNoticeDao newsNoticeDao;

	public DataGrid pressList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = newsNoticeDao.pressList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}

	public Map<String, String> pressDetails(Long id) {
		return newsNoticeDao.pressDetails(id);
	}

	public int insert(NewsNotice newsNotice) {
		// TODO Auto-generated method stub
		return newsNoticeDao.insert(newsNotice);
	}

	public int update(NewsNotice newsNotice) {
		// TODO Auto-generated method stub
		return newsNoticeDao.update(newsNotice);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return newsNoticeDao.delete(id);
	}

	
	
}
