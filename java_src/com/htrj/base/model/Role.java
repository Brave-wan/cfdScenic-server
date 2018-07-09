package com.htrj.base.model;

import com.htrj.common.base.BaseEntity;

/**
 * 张腾跃
 * 2016年9月29日15:05:15
 * 角色权限表
 * @author Administrator
 *
 */
public class Role extends BaseEntity{
private Integer id;
private Integer brid;//角色id
private String menuid;//菜单id
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getBrid() {
	return brid;
}
public void setBrid(Integer brid) {
	this.brid = brid;
}
public String getMenuid() {
	return menuid;
}
public void setMenuid(String menuid) {
	this.menuid = menuid;
}

}
