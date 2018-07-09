package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.HtmlText;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class HtmlTextDao extends BaseDao<HtmlText, Integer>{
	/**
	 * 根据商品ID获取详情html
	 */
	public String getContentUrlById(Long goodsId){
		return getSqlSession().selectOne(getStatementName("getContentUrlById"),goodsId);
	}

	public Map<String, String> wetLandSynopsis(Integer type) {
		return getSqlSession().selectOne(getStatementName("wetLandSynopsis"),type);
	}

	public Map<String,String> selectDetailHtmlById(Long noticeId) {
		return getSqlSession().selectOne(getStatementName("selectDetailHtmlById"),noticeId);
	}

	public String getNoticeByNoticeId(Long notice_id) {
		return getSqlSession().selectOne(getStatementName("getNoticeByNoticeId"),notice_id);
	}

	public String getPlanningOrIntroduce(Integer type) {
		return getSqlSession().selectOne(getStatementName("getPlanningOrIntroduce"),type);
	}

    public int addHtmlText(HtmlText htmlText) {
        return getSqlSession().insert(getStatementName("insertSelective"),htmlText);
    }

    public int updateHtmlTest(HtmlText htmlText) {
        return getSqlSession().update(getStatementName("updateByPrimaryKey"),htmlText);
    }

    public HtmlText selectByPrimaryKey(Long id) {
        return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
    }

	public String getAboutUs() {
		return getSqlSession().selectOne(getStatementName("getAboutUs"));
	}

	public String getDetailUrlbyId(Long detailId) {
		return getSqlSession().selectOne(getStatementName("getDetailUrlbyId"),detailId);
	}

	public Page gethtmlList(PageRequest<Map<String, Object>> pageRequest) {
		// TODO Auto-generated method stub
		return pageQuery(getStatementName("gethtmlList"),pageRequest);
	}

	public int deleteById(long id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(getStatementName("delete"),id);
	}
}
