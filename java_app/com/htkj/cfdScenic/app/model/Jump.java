package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

/**
 * 广告（轮播图，广告位）
 * @author:lishilong
 * @date:2016年7月27日
 */
public class Jump extends BaseEntity{

	/**
	 * Title:
	 * @author:lishilong
	 * @date:2016年11月12日
	 */
	private static final long serialVersionUID = 319897780069428064L;
	
	private Long id;
	private String name;
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
	
}
