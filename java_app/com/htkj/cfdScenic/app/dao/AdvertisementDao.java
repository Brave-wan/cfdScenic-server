package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.Advertisement;
import com.htkj.cfdScenic.app.model.Jump;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AdvertisementDao extends BaseDao<Advertisement, Integer>{
	/**
	 * 查询轮播图
	 */
	public List<Map<String,String>> findPic(){
		return getSqlSession().selectList(getStatementName("findPic"));
	}

	public List<Map<String, String>> selectImgUrl() {
		return getSqlSession().selectList(getStatementName("selectImgUrl"));
	}

	public List<Map<String, String>> selectPrimarySideImgUrl() {
		return getSqlSession().selectList(getStatementName("selectPrimarySideImgUrl"));
	}

	public List<Map<String, String>> adPositionId() {
		return getSqlSession().selectList(getStatementName("adPositionId"));
	}

	public List getImg() {
		return getSqlSession().selectList(getStatementName("getImg"));
	}

	public Page pageGetAdvertisement(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pageGetAdvertisement"),pageRequest);
	}

	public int insert(Advertisement advertisement){
		return getSqlSession().insert(getStatementName("insertSelective"),advertisement);
	}

	public int deleteAdvertisement(Long id) {
		return getSqlSession().delete(getStatementName("deleteByPrimaryKey"),id);
	}

	public int updateByPrimaryKeySelective(Advertisement advertisement) {
	    return  getSqlSession().update(getStatementName("updateByPrimaryKeySelective"),advertisement);
	}

    public Advertisement selectByPrimaryKey(Long id) {
        return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
    }

	public List<Jump> getVisitorsAndShopInformation() {
		return getSqlSession().selectList(getStatementName("getVisitorsAndShopInformation"));
	}

	public List<Map> getJumpType(Map map) {
		return getSqlSession().selectList(getStatementName("getJumpType"),map);
	}

	public List<Map<String, String>> getCarouselImg() {
		return getSqlSession().selectList(getStatementName("getCarouselImg"));
	}
}
