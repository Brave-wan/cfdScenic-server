package com.htrj.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htrj.base.dao.BaseRoleDao;
import com.htrj.base.model.BaseRole;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class BaseRoleService {

	@Autowired
	private BaseRoleDao baseRoleDao;
	
	/**
	 * 
	 * @Title: getBaseRoleByParam 
	 * @Description: TODO(根据参数查询角色列表) 
	 * @param: @param baseRole 参数封装的实体
	 * @param: @return    
	 * @return: List<BaseRole> 查询到的角色列表
	 * @throws
	 */
	public List<BaseRole> getBaseRoleByParam(BaseRole baseRole){
		return baseRoleDao.getBaseRoleByParam(baseRole);
	}
	
	/**
	 * 
	 * @Title: pageBaseRoleByParam 
	 * @Description: TODO(根据参数查询角色列表_分页) 
	 * @param: @param pageRequest
	 * @param: @return    
	 * @return: Page    
	 * @throws
	 */
	public DataGrid pageBaseRoleByParam(PageRequest<Map<String, Object>> pageRequest){
		Page returnPage = baseRoleDao.pageBaseRoleByParam(pageRequest);
		DataGrid dataGrid=DataGrid.pageToDataGrid(returnPage);
		return dataGrid;
	}
	
	/**
	 * 
	 * @Title: addBaseRole 
	 * @Description: TODO(添加角色) 
	 * @param: @param baseRole 要添加的角色实体
	 * @param: @return    
	 * @return: int 返回操作数据库的行数
	 * @throws
	 */
	public int addBaseRole(BaseRole baseRole){
		return baseRoleDao.addBaseRole(baseRole);
	}
	
	/**
	 * 
	 * @Title: upBaseRoleById 
	 * @Description: TODO(根据ID修改角色属性) 
	 * @param: @param baseRole 角色属性
	 * @param: @return    
	 * @return: int 返回操作数据库的行数
	 * @throws
	 */
	public int upBaseRoleById(BaseRole baseRole){
		return baseRoleDao.upBaseRoleById(baseRole);
	}
	
	/**
	 * 
	 * @Title: delBaseRoleById 
	 * @Description: TODO(根据ID删除角色) 
	 * @param: @param brId 角色ID
	 * @param: @return    
	 * @return: int 返回操作数据库的行数
	 * @throws
	 */
	public int delBaseRoleById(Long brId){
		return baseRoleDao.delBaseRoleById(brId);
	}
}
