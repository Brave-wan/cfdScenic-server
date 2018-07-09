package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;
/**
 * 汇总表（赞，评论数，分享）
 * @author:lishilong
 * @date:2016年7月27日
 */
public class CommentSum extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -2662226336704964181L;
	private Long id;//主键
	private Integer favor;//赞
	private Integer comment;//评论
	private Integer share;//分享
	private Long linkId;//关联id
	private Long userId;//用户id
	private String createDate;//时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getFavor() {
		return favor;
	}
	public void setFavor(Integer favor) {
		this.favor = favor;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public Integer getShare() {
		return share;
	}
	public void setShare(Integer share) {
		this.share = share;
	}
	public Long getLinkId() {
		return linkId;
	}
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
