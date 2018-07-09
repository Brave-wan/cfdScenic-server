package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

/**
 * 平台用户
 * @author:lishilong
 * @date:2016年7月27日
 */
public class SysUser extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -6252767488434910443L;
	private Long id;//主键
	private String name;//账户
	private String passWord;//密码
	private Integer state;//状态（0正常1停用）
	private String createTime;//日期
	private String telPhone;//日期
	private Integer sex;//性别(0男1女)
	private Long BRID;
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
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getBRID() {
		return BRID;
	}
	public void setBRID(Long bRID) {
		BRID = bRID;
	}
	
	
}
