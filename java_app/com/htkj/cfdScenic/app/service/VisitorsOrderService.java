package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.VisitorsOrderDao;
import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VisitorsOrderService {
	@Autowired
	private VisitorsOrderDao visitorsOrderDao;

	public Integer insertMessage(VisitorsOrder visitorsOrder) {
		return visitorsOrderDao.insertMessage(visitorsOrder);
	}

	public VisitorsOrder selectByUserId(Long userId) {
		return visitorsOrderDao.selectByUserId(userId);
	}

	public void updateStateMessage(Map<String, Object> map) {
		visitorsOrderDao.updateStateMessage(map);
	}
	
	public void updatePaystate(Map<String, Object> map) {
		visitorsOrderDao.updatePayStatus(map);
	}
	

	public DataGrid selectOrderList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = visitorsOrderDao.selectOrderList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}

	public Map<String, Object> selectById(Long id) {
		return visitorsOrderDao.selectById(id);
	}

	public DataGrid getVisitorsOrderWeiList(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = visitorsOrderDao.getVisitorsOrderWeiList(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			throw new BusinessException("查询未使用门票信息列表出错",e);
		}
		return data;
	}
	public DataGrid getVisitorsOrderYiList(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = visitorsOrderDao.getVisitorsOrderYiList(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			throw new BusinessException("查询已使用门票信息列表出错",e);
		}
		return data;
	}

	public Map<String, Object> getVisitorsOrderById(Long orderCode) {
		return visitorsOrderDao.getVisitorsOrderById(orderCode);
	}

	public List<Map<String, Object>> findOrderByOrderId(Long orderCode) {
		return visitorsOrderDao.findOrderByOrderId(orderCode);
	}
	
	public Map<String, Object> selectByPrimaryKey(Long id){
		return visitorsOrderDao.selectByPrimaryKey(id);
	}

	public int updateVisitorsOrder(VisitorsOrder visitorsOrder) {
		return visitorsOrderDao.updateVisitorsOrder(visitorsOrder);
	}

	public int deleteVisitorsOrder(Long id) {
		return visitorsOrderDao.deleteVisitorsOrder(id);
	}

    public DataGrid getVisitorsOrderList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data = new DataGrid();
        try {
            Page page = visitorsOrderDao.getVisitorsOrderList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
            throw new BusinessException("查询已使用门票信息列表出错",e);
        }
        return data;
    }

    public Double getTodayTurnover() {
        return visitorsOrderDao.getTodayTurnover();
    }

    public Double getTurnover() {
        return visitorsOrderDao.getTurnover();
    }

    public VisitorsOrder selectByOrderCode(String orderCode) {
        return visitorsOrderDao.selectByOrderCode(orderCode);
    }

    public Map<String, Object> selectByCode(String orderCode) {
        return visitorsOrderDao.selectByCode(orderCode);
    }

    public DataGrid getInvoiceList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data = new DataGrid();
        try {
            Page page = visitorsOrderDao.getInvoiceList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
            throw new BusinessException("查询开发票列表出错",e);
        }
        return data;
    }

	public String getOrderCodeByOrderId(Long orderId) {
		return visitorsOrderDao.getOrderCodeByOrderId(orderId);
	}
}
