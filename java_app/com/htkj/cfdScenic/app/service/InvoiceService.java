package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.InvoiceDao;
import com.htkj.cfdScenic.app.model.Invoice;
import com.htrj.common.base.BaseService;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
@Service
@Transactional
public class InvoiceService extends BaseService{
	/**
	 * 开票功能
	 * 张腾跃
	 * 2016年10月19日10:55:19
	 */
	@Autowired
	private InvoiceDao dao;

	public DataGrid SelectAll(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = dao.SelectAll(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	//新增
	public int saveInvoice(Invoice invoice){
		return dao.saveInvoice(invoice);
	}
	//修改
	public int updateById(Invoice invoice){
		return dao.updateById(invoice);
	}
	//删除
	public int deleteInvoice(Long id){
		return dao.deleteInvoice(id);
	}
	public Invoice SelectById(Long id) {
		// TODO Auto-generated method stub
		return dao.SelectById(id);
	}

    public int insertSelective(Invoice invoice) {
        return dao.insertSelective(invoice);
    }}
