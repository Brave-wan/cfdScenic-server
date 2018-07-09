package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class Express extends BaseEntity{
	private Long id;//主键ID
	private	Long userId;//用户ID
	private Long orderCode;//订单号
	private String expressName;//快递名称
	private String expressCode;//快递单号
	private	String createDate;//创建时间
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
	public Long getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
