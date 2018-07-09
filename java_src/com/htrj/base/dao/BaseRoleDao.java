package com.htrj.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htrj.base.model.BaseRole;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class BaseRoleDao extends BaseDao<BaseRole, Integer>  {
	
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
		List<BaseRole> baseRoleList = getSqlSession().selectList(getStatementName("getBaseRoleByParam"), baseRole);
		return baseRoleList;
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
	public Page pageBaseRoleByParam(PageRequest<Map<String, Object>> pageRequest){
		return pageQuery(getStatementName("pageBaseRoleByParam"), pageRequest);
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
		int count = getSqlSession().insert(getStatementName("addBaseRole"), baseRole);
		return count;
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
		int count = getSqlSession().insert(getStatementName("upBaseRoleById"), baseRole);
		return count;
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
		int count = getSqlSession().delete(getStatementName("baseRole"), brId);
		return count;
	}
}
