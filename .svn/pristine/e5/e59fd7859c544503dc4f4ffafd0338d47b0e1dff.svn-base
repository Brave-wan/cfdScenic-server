package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htrj.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PictureLibraryDao extends BaseDao<PictureLibrary, Integer>{
	
	/**
	 *	根据商品ID获取商品的轮播图
	 */
	public List getPicById(Long goodsId){
		return getSqlSession().selectList(getStatementName("getPicById"),goodsId);
	}
	/**
	 * 根据店铺的Id获取店铺轮播图
	 */
	public List findHotelPicBy(Long id) {
		return getSqlSession().selectList(getStatementName("findHotelPicBy"),id);
	}
	public void saveCommentPic(PictureLibrary pictureLibrary) {
		getSqlSession().insert(getStatementName("saveCommentPic"),pictureLibrary);
	}
	public List<String> findAtlasByVisitorsId(Long visitorsId) {
		return getSqlSession().selectList(getStatementName("findAtlasByVisitorsId"),visitorsId);
	}
	public List<String> selectPictureLibrary(Long id) {
		return getSqlSession().selectList(getStatementName("selectPictureLibrary"),id);
	}
	public String getPhotoOfHomePage(Integer type) {
		return getSqlSession().selectOne(getStatementName("getPhotoOfHomePage"),type);
	}
	public List<String> getPicByTravelId(Long travelId) {
		return getSqlSession().selectList(getStatementName("getPicByTravelId"),travelId);
	}
	public void savePictureLibrary(PictureLibrary pl) {
		getSqlSession().insert(getStatementName("savePictureLibrary"),pl);
	}
	public void savePictureLibraryAll(PictureLibrary pictureLibrary) {
		getSqlSession().insert(getStatementName("savePictureLibrary"),pictureLibrary);
	}
	public List<String> getPicByUserCommentId(Long userCommentId) {
		return getSqlSession().selectList(getStatementName("getPicByUserCommentId"),userCommentId);
	}
    public void deleteByLinkId(Long id) {
        getSqlSession().delete(getStatementName("deleteByLinkId"),id);
    }
	public List<String> selectPictureLibraryByVisitorsId(Long id) {
		return getSqlSession().selectList(getStatementName("selectPictureLibraryByVisitorsId"),id);
	}
}
