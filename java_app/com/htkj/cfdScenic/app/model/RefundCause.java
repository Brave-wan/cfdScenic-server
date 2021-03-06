package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class RefundCause extends BaseEntity{
	private Long id;		//订单ID
	private Long userId;	//用户ID
	private Long orderCode;	//订单号
	private String userName;//姓名
	private String userPhone;//手机号
	private String cause;	//原因
	private String createDate;//创建时间
	private Long shopInformationId;//店铺
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getShopInformationId() {
		return shopInformationId;
	}
	public void setShopInformationId(Long shopInformationId) {
		this.shopInformationId = shopInformationId;
	}
	
}
