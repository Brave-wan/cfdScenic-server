package com.htkj.cfdScenic.app.model;

import java.math.BigDecimal;

import com.htrj.common.base.BaseEntity;

public class UserAccount extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = 6710498257224183607L;
	private Long id;				//用户账户表id
	private Long userId;			//用户id
	private Double balance;		//余额
	private Integer integration;	//积分
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
	public Integer getIntegration() {
		return integration;
	}
	public void setIntegration(Integer integration) {
		this.integration = integration;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
