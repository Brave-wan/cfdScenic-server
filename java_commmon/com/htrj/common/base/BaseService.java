package com.htrj.common.base;

import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional(readOnly = true)
public abstract class BaseService {
	public final Logger	logger	= LoggerFactory.getLogger(getClass());

	/**
	 * 审批方法结束时的回调方法
	 * 
	 * @param wkexid
	 * @param state
	 * @param approvalview
	 */
	@Transactional(readOnly = false)
	public void workFlowCallBack(Integer wkexid, Integer state, String approvalview) {
	};

}
