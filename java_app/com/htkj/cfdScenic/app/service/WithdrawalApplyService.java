package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.WithdrawalApplyDao;
import com.htkj.cfdScenic.app.model.WithdrawalApply;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class WithdrawalApplyService {

	@Autowired
	private WithdrawalApplyDao withdrawalApplyDao;
	
	public DataGrid getWithdrawalApplyManage(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = withdrawalApplyDao.getWithdrawList(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public int updateByPrimaryKeySelective(WithdrawalApply withdrawalApply) {
		return withdrawalApplyDao.updateByPrimaryKeySelective(withdrawalApply);
	}
}
