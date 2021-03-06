package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.UserAddress;
import com.htrj.common.base.BaseDao;

@Repository
public class UserAddressDao extends BaseDao<UserAddress, Integer>{
	/**
	 * 查询地址
	 */
	public Map getUserAdderssByUserId(Long userId){
		return getSqlSession().selectOne(getStatementName("getUserAdderssByUserId"),userId);
	}

	public Integer selectByUser(Long userId) {
		return getSqlSession().selectOne(getStatementName("selectByUser"),userId);
	}

	public void userAddress(UserAddress userAddress) {
		getSqlSession().insert(getStatementName("userAddress"),userAddress);
	}

	public UserAddress selectById(Long id) {
		return getSqlSession().selectOne(getStatementName("selectById"),id);
	}

	public void updateMessage(UserAddress userAddress) {
		getSqlSession().update(getStatementName("updateMessage"),userAddress);
	}

	public void editAllDefault(Long userId) {
		getSqlSession().update(getStatementName("editAllDefault"),userId);
	}

	public void updateDefault(Long id) {
		getSqlSession().update(getStatementName("updateDefault"),id);
	}

	public void deleteAddress(Long id) {
		getSqlSession().update(getStatementName("deleteAddress"),id);
	}

	public List<Map<String, String>> addressList(Long userId) {
		return getSqlSession().selectList(getStatementName("addressList"),userId);
	}

	public Map<String, String> selectDefaultAddress(Long userId) {
		return getSqlSession().selectOne(getStatementName("selectDefaultAddress"),userId);
	}

	public void deleteAddressById(Long id) {
		getSqlSession().delete(getStatementName("deleteAddressById"),id);
	}

	public void updateAddressById(Long id) {
		getSqlSession().update(getStatementName("updateAddressById"),id);
	}
}
