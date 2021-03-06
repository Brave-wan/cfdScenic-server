package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.ConsumerUserDao;
import com.htkj.cfdScenic.app.dao.ShopGoodsDao;
import com.htkj.cfdScenic.app.dao.UserAccountDao;
import com.htkj.cfdScenic.app.dao.UserAccountLogDao;
import com.htkj.cfdScenic.app.dao.UserBankDao;
import com.htkj.cfdScenic.app.dao.VisitorsDao;
import com.htkj.cfdScenic.app.dao.VisitorsOrderDao;
import com.htkj.cfdScenic.app.dao.WithdrawalApplyDao;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.UserBank;
import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htkj.cfdScenic.app.model.WithdrawalApply;
import com.htrj.common.base.BaseService;

@Service
@Transactional
public class MyPurseService extends BaseService{
	
	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserBankDao userBankDao;
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	@Autowired
	private ConsumerUserDao consumerUserDao;
	@Autowired
	private VisitorsDao visitorsDao;
	@Autowired
	private VisitorsOrderDao visitorsOrderDao;
	@Autowired
	private WithdrawalApplyDao withdrawalApplyDao;
	/**
	 * 获取我的余额
	 * @param userId 
	 */
	public Map getMyBalance(Long userId){
		return userAccountDao.getMyBalance(userId);
	}
	/**
	 * 获取银行卡列表 
	 */
	public List getBank(Long userId) {
		return userBankDao.getBank(userId);
	}
	/**
	 * 获取交易记录明细 
	 */
	public List getTradeLog(Map map) {
		return userAccountLogDao.getTradeLog(map);
	}
	/**
	 * 获取交易记录明细 
	 */
	public List getShopTradeLog(Map map) {
		return userAccountLogDao.getShopTradeLog(map);
	}
	/**
	 * 获取支付密码
	 */
	public String getPayPassWord(Long userId) {
		return consumerUserDao.getPayPassWord(userId);
	}
	/**
	 * 更新支付密码
	 */
	public void updatePayPassWord(Map map) {
		consumerUserDao.updatePayPassWord(map);
	}
	/**
	 * 添加银行卡
	 */
	public void saveBank(UserBank userBank) {
		userBankDao.saveBank(userBank);
	}
	public void saveUserAccountLog(UserAccountLog userAccountLog) {
		userAccountLogDao.saveUserAccountLog(userAccountLog);
	}
	/**
	 * 更新余额
	 * @param type 
	 */
	public void updateBalanceUserAccount(Map map, int type) {
		switch (type) {
		case 0:
			userAccountDao.updateBalanceUserAccount(map);
			break;
		case 1:
			userAccountDao.updateCutDowmBalanceUserAccount(map);
			break;
		}
	}
	public Map getBankByIdCardAndCardNumber(Map map) {
		return userBankDao.getBankByIdCardAndCardNumber(map);
	}
	/**
	 * 获取我的积分
	 */
	public Map getMyIntegral(Long userId) {
		return userAccountDao.getMyIntegral(userId);
	}
	/**
	 * 获取积分兑换商品列表
	 */
	public List getIntegralGoods() {
		return visitorsDao.getIntegralGoods();
	}
	/**
	 * 获取积分商品详情
	 */
	public Map getIntegralGoodsDetaili(Long id) {
		return visitorsDao.getIntegralGoodsDetaili(id);
	}
	/**
	 * 保存积分订单
	 */
	public void saveIntegralOrder(VisitorsOrder visitorsOrder) {
		visitorsOrderDao.insertMessage(visitorsOrder);
	}
	/**
	 * 回显订单
	 */
	public Map selectVisitorsOrder(Long id) {
		return visitorsOrderDao.selectVisitorsOrder(id);
	}
	/**
	 * 支付回显订单
	 */
	public Map selectVisitorsOrderFinsh(Long id) {
		return visitorsOrderDao.selectVisitorsOrderFinsh(id);
	}
	/**
	 * 我所有的订单
	 */
	public List selectMyAllVisitorsOrder(Long userId) {
		return visitorsOrderDao.selectMyAllVisitorsOrder(userId);
	}
	/**
	 * 我的订单详情
	 */
	public Map selectVisitorsOrderDetail(Long id) {
		return visitorsOrderDao.selectVisitorsOrderDetail(id);
	}
	public void insertUserAccount(UserAccount userAccount) {
		userAccountDao.insertUserAccount(userAccount);
	}
	public Integer getIntegrationByUserId(Long userId) {
		return userAccountDao.getIntegrationByUserId(userId);
	}
	public void saveWithdrawalApply(WithdrawalApply withdrawalApply) {
		withdrawalApplyDao.saveWithdrawalApply(withdrawalApply);
	}
}
