package com.htrj.common.page;

import java.util.Map;

import com.htrj.common.base.BaseEntity;
import com.htrj.common.utils.StringUtil;

public class PagerForm extends BaseEntity{
	private static final long serialVersionUID = -6425724913518228942L;
	private int	page=1;
	private int	rows=10;
	private String sort;
	private String orderDirection="asc";
	private int	pageNumShown;
	private int	currentPage;
	private int total;

	/**
	 * @Title: getPageRequest 
	 * @Description: TODO(获得分页封装的实体) 
	 * @param map
	 * @return    PageRequest<Map<String,Object>>   返回类型 
	 * @throws
	 */
	public PageRequest<Map<String, Object>> getPageRequest(Map<String, Object> map){
		PageRequest<Map<String, Object>> pageRequest = new PageRequest<Map<String, Object>>(this.getPage(), this.getRows(), map, this.getSort());
		return pageRequest;
	}
	
	
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public PagerForm setTotleCount(int totalCount){
		total = totalCount;
		return this;
	}
	
	/**
	 * @Title: getMysqlOrder 
	 * @Description: TODO(获得排序语句) 
	 * @param defaultOrder 如果排序为空 则默认排序语句
	 * @return    String   排序语句 
	 * @throws
	 */
	public String getMysqlOrder(String defaultOrder){
		String orderStr = "";
		if(!StringUtil.isEmpt(this.getSort())){
			orderStr = " order by " + this.getSort();
			if(!StringUtil.isEmpt(this.getOrderDirection())){
				orderStr = orderStr + " " + this.getOrderDirection();
			}
		}else{
			orderStr = defaultOrder;
		}
		return orderStr;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public int getPageNumShown() {
		return pageNumShown;
	}

	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotal() {
		return total;
	}
	
}
