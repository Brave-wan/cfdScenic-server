package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.HtmlTextDao;
import com.htkj.cfdScenic.app.model.HtmlText;
import com.htrj.common.base.BaseService;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class HtmlTextService extends BaseService{
	@Autowired
	private HtmlTextDao htmlTextDao;

	public Map<String, String> wetLandSynopsis(Integer type) {
		return htmlTextDao.wetLandSynopsis(type);
	}


    public int addHtmlText(HtmlText htmlText) {
        return htmlTextDao.addHtmlText(htmlText);
    }

    public int updateHtmlTest(HtmlText htmlText) {
        return htmlTextDao.updateHtmlTest(htmlText);
    }

    public HtmlText selectByPrimaryKey(Long id) {
        return htmlTextDao.selectByPrimaryKey(id);
    }
    public DataGrid gethtmlList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data;
        try {
            Page page = htmlTextDao.gethtmlList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
            throw new BusinessException("查询商品订单信息列表出错",e);
        }
        return data;
    }


	public int delete(long id) {
		// TODO Auto-generated method stub
		return htmlTextDao.deleteById(id);
	}
}
