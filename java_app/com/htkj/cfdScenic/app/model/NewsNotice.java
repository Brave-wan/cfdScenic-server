package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;
/**
 * 新闻须知
 * @author:lishilong
 * @date:2016年7月27日
 */
public class NewsNotice extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = 6943567566394800843L;
	private Long id;//主键
	private String name;//名称
	private String newsDescribe;//描述
	private String headerImg;//图片
	private String content;//内容
	private String creator;//创建人
	private String createTime;//创建时间
	private Long creatorId;//创建人id
	private Integer type;//类型（1.新闻须知，2景区规划）
	private String contentUrl;//内容地址
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewsDescribe() {
		return newsDescribe;
	}
	public void setNewsDescribe(String newsDescribe) {
		this.newsDescribe = newsDescribe;
	}
	public String getHeaderImg() {
		return headerImg;
	}
	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	
}
