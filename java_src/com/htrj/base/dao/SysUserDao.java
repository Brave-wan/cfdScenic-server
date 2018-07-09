package com.htrj.base.dao;

import org.springframework.stereotype.Repository;

import com.htrj.base.model.SysUser;
import com.htrj.common.base.BaseDao;

/**
 * 
 * @ClassName: BaseUserDao
 * @Description: 后台管理用户DAO操作类
 * 
 * @author 章旭
 * @Company: 石家庄华腾软件科技有限公司
 * @date 2016年5月9日 下午2:51:53 
 *
 */
@Repository
public class SysUserDao extends BaseDao<SysUser, Integer> {

	/**
	 * 
	 * @Title: findUserByAccount 
	 * @Description: TODO(通过用户名查询后台管理用户) 
	 * @param: @param buAccount 用户名 账号
	 * @param: @return    
	 * @return: BaseUser 用户实体
	 * @throws
	 */
	public SysUser findUserByAccount(String name){
		SysUser sysUser = getSqlSession().selectOne(getStatementName("findUserByAccount"), name);
		return sysUser;
	}

}
