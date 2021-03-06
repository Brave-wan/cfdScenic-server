package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.ExpressDao;
import com.htkj.cfdScenic.app.dao.GoodsOrderDao;
import com.htkj.cfdScenic.app.model.Express;
import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author wangfenglong
 * @date 2016/10/11 001110:06.
 */
@Service
@Transactional
public class GoodsOrderService {

    @Autowired
    private GoodsOrderDao goodsOrderDao;
    @Autowired
    private ExpressDao expressDao;


    public DataGrid getGoodsOrderList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data;
        try {
            Page page = goodsOrderDao.getGoodsOrderList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
            throw new BusinessException("查询商品订单信息列表出错",e);
        }
        return data;
    }

    public Map<String, Object> selectByPrimaryKey(Long id) {
        return goodsOrderDao.selectByPrimaryKey(id);
    }

    public void deleteGoodsOrderById(Long id) {
        goodsOrderDao.deleteGoodsOrderById(id);
    }

    public void updateByOrderCode(GoodsOrder goodsOrder) {
        goodsOrderDao.updateByOrderCode(goodsOrder);
    }

	public int updateByOrder(GoodsOrder goodsOrder) {
		// TODO Auto-generated method stub
		return goodsOrderDao.updateByOrder(goodsOrder);
	}

	public int updateBillingByOrderCode(GoodsOrder goodsOrder) {
		// TODO Auto-generated method stub
		return goodsOrderDao.updateBillingByOrderCode(goodsOrder);
	}

	public Map<String, Object> selectorderCode(long code) {
		// TODO Auto-generated method stub
		return goodsOrderDao.selectorderCode(code);
	}

    public Double getTurnover(Long shopInformationId) {
        return goodsOrderDao.getTurnover(shopInformationId);
    }

    public Double getTodayTurnover(Long shopInformationId) {
        return goodsOrderDao.getTodayTurnover(shopInformationId);
    }

	public void updateDeleverFeeByOrderCode(Map map) {
		goodsOrderDao.updateDeleverFeeByOrderCode(map);
	}

	public void saveExpress(Express express) {
		expressDao.saveExpress(express);
	}
}
