package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class AlipayInfo extends BaseEntity{
	/**
	 * Title:
	 * @author:lishilong
	 * @date:2016年11月8日
	 */
	private static final long serialVersionUID = 5576428870967194776L;
	
	private Long id;
	private Long shopUserId;
	private String partner;
	private String seller;
	private String privateKey;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShopUserId() {
		return shopUserId;
	}
	public void setShopUserId(Long shopUserId) {
		this.shopUserId = shopUserId;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
