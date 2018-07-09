package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.Invoice;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class InvoiceDao extends BaseDao<Invoice,Integer>{

	/**
	 * 开票功能
	 * 张腾跃
	 * 2016年10月19日10:51:52
	 * @param pageRequest
	 * @return
	 */
	//查看所有并加搜索条件
		public Page SelectAll(PageRequest<Map<String, Object>> pageRequest) {
			return pageQuery(getStatementName("SelectAll"), pageRequest);
		}
	//新增
		public int saveInvoice(Invoice invoice){
			return getSqlSession().insert(getStatementName("saveInvoice"),invoice);
		}
		//修改
		public int updateById(Invoice invoice){
			return getSqlSession().update(getStatementName("updateById"),invoice);
		}
		//删除
		public int deleteInvoice(Long id){
			return getSqlSession().delete(getStatementName("deleteInvoice"),id);
		}
		public Invoice SelectById(Long id) {
			// TODO Auto-generated method stub
			return getSqlSession().selectOne(getStatementName("SelectById"),id);
		}
        public int insertSelective(Invoice invoice) {
            return getSqlSession().insert(getStatementName("insertSelective"),invoice);
        }
}
