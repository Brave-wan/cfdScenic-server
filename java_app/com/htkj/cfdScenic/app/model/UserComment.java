package com.htkj.cfdScenic.app.model;

import java.util.ArrayList;
import java.util.List;

import com.htrj.common.base.BaseEntity;

public class UserComment extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = 7575340521434363758L;
	private Long id;				//评论表id
	private Long userId;			//用户id
	private Long fromUserId;		//发布人id
	private Long linkId;			//评论关联id（1 景区评论  等）
	private Integer commentType;	//评论类型（1,景区等）
	private Integer contentType;	//评论类型（0评论1回复）
	private String content;			//评论内容
	private Integer satisfyState;	//满意度（1.非常满意 2 满意 3不满意）
	private String createTime;		//评论时间
	private Integer haveImg;		//是否有图片
	private String memo;			//备注
	private Integer isTravels;		//是否同步到游乐圈
	private String nickName;		//昵称
	private String headImg;			//头像
	private String visitorsName;	//景区名称
	private String visitorsId;		//景区Id
	private List<PictureLibrary> picList=new ArrayList<PictureLibrary>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getLinkId() {
		return linkId;
	}
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	public Integer getCommentType() {
		return commentType;
	}
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSatisfyState() {
		return satisfyState;
	}
	public void setSatisfyState(Integer satisfyState) {
		this.satisfyState = satisfyState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getHaveImg() {
		return haveImg;
	}
	public void setHaveImg(Integer haveImg) {
		this.haveImg = haveImg;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getIsTravels() {
		return isTravels;
	}
	public void setIsTravels(Integer isTravels) {
		this.isTravels = isTravels;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public List<PictureLibrary> getPicList() {
		return picList;
	}
	public void setPicList(List<PictureLibrary> picList) {
		this.picList = picList;
	}
	public String getVisitorsName() {
		return visitorsName;
	}
	public void setVisitorsName(String visitorsName) {
		this.visitorsName = visitorsName;
	}
	public Long getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	public String getVisitorsId() {
		return visitorsId;
	}
	public void setVisitorsId(String visitorsId) {
		this.visitorsId = visitorsId;
	}
	
	
	
}
