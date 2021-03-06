package com.htrj.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.htrj.base.model.BaseMenu;
import com.htrj.common.base.BaseDao;

/**
 * @ClassName: BaseMenuDao
 * @Description: 后台菜单
 * 
 * @author 章旭
 * @Company: 石家庄华腾软件科技有限公司
 * @date 2016年5月9日 下午2:58:42 
 *
 */
@Repository
public class BaseMenuDao extends BaseDao<BaseMenu, Integer>  {

	/**
	 * 
	 * @Title: finUserMenu 
	 * @Description: TODO(根据参数查询菜单信息，用于登录后进入主页面左侧的列表显示。) 
	 * @param: @param BaseMenu 封装的参数信息
	 * @param: @return 
	 * @return: List<BaseMenu> 查询到的菜单列表   
	 * @throws
	 */
	public List<BaseMenu> findUserMenu(BaseMenu BaseMenu){
		List<BaseMenu> baseMenu = getSqlSession().selectList(getStatementName("findUserMenu"), BaseMenu);
		return baseMenu;
	}
	public List<BaseMenu> findUMenu(BaseMenu BaseMenu){
		List<BaseMenu> baseMenu = getSqlSession().selectList(getStatementName("findUMenu"), BaseMenu);
		return baseMenu;
	}
	public List<BaseMenu> finderjiMenu(BaseMenu BaseMenu){
		List<BaseMenu> baseMenu = getSqlSession().selectList(getStatementName("finderjiMenu"), BaseMenu);
		return baseMenu;
	}
	

}
