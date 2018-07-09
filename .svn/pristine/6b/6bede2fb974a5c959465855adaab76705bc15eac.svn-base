package com.htrj.base.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htrj.base.dao.SysUserDao;
import com.htrj.base.model.SysUser;
import com.htrj.common.exception.BusinessException;

@Service
@Transactional
public class SysUserService {
	
	@Autowired(required=false)
	private SysUserDao sysUserDao;
	
	/**
	 * 登录
	 * @param userAccount 用户名
	 * @param userpwd 密码
	 * @return 
	 * @throws BusinessException
	 */
	public SysUser login(String name, String password)throws BusinessException{
		SysUser user=sysUserDao.findUserByAccount(name);
		if(user==null){
			throw new BusinessException("用户账户不存在！");
		}
		if(user.getState()==1){
			throw new BusinessException("您的账户已被禁用！");
		}
		
		String pas = new Md5Hash(password).toHex();
		
		String userPas = user.getPassword();
		
		
		if(!userPas.equals(pas)){
			throw new BusinessException("密码错误！");
		}
		return user;
	}
}
