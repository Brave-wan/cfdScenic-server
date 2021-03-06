package com.htrj.common.page;

import java.util.List;

/**
 * easyui的datagrid模型
 * 
 * 
 */
public class DataGrid implements java.io.Serializable {
    private static final long serialVersionUID = 4107978380331258400L;
    private int               total;                                  // 总记录数
    private List              rows;                                   // 每行记录
    private int lastPage;
    
    /**
     * 
     * @Title: pageToDataGrid 
     * @Description: TODO(将Page实体转化为DataGrid) 
     * @param: @param returnPage
     * @param: @return    
     * @return: DataGrid    
     * @throws
     */
    public static DataGrid pageToDataGrid(Page returnPage){
    	DataGrid dataGrid = new DataGrid(returnPage.getTotalCount(),returnPage.getLastPageNumber(), returnPage.getResult());
    	return dataGrid;
    }
    
    public DataGrid(){
    	
    }
    
    public DataGrid(int total,int lastPage, List rows){
    	this.total = total;
    	this.lastPage=lastPage;
    	this.rows = rows;
    }
                                                                       
    @SuppressWarnings("rawtypes")
    public List getRows() {
        return rows;
    }
    
    @SuppressWarnings("rawtypes")
    public void setRows(List rows) {
        this.rows = rows;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
    
}
