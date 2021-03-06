package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class PushMessage extends BaseEntity{
	/**
	 * @date:2016年10月7日
	 */
	private static final long serialVersionUID = 8607191699958558499L;
	private Long id;//主键ID
	private Long userId;//用户ID
	private Long detailId;//详情表ID
	private String image;//图片地址
	private String orderCode;//订单号
	private Integer type;//消息类型（0系统1订单）
	private String title;//标题
	private String createDate;//创建时间
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
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
