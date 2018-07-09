package com.htrj.common.page;

import com.htrj.common.base.BaseEntity;

public class PageCount extends BaseEntity{
	private static final long serialVersionUID = -6425724913518228942L;
	private Integer page=1;
	private Integer rows=10;
	
	
	
	public Integer getPage() {
		return (page-1)*rows;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return (page-1)*this.rows+rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	

}
