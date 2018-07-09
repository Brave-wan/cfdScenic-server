package com.htrj.base.model;

import com.htrj.common.base.BaseEntity;

public class SysUser extends BaseEntity{
	private Long 		id;				//平台用户id
	private String    	name;           //账户
	private String  	password;		//密码
	private Integer     state;        	//状态 （0正常1停用）
	private String  	createtime;		//日期
	private String  	telphone;		//电话
	private Integer     sex;     		//性别(0男1女)
	private Long	 	BRID;			
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
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
