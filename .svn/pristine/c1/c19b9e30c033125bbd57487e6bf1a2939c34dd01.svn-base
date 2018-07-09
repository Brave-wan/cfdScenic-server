package com.htkj.cfdScenic.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.SysVerificationDao;
import com.htkj.cfdScenic.app.model.SysVerification;

@Service
@Transactional
public class SysVerificationService {
	@Autowired
	private SysVerificationDao sysVerificationDao;

	public SysVerification getMessage(String mobileNo) {
		return sysVerificationDao.getMessage(mobileNo);
	}

	public void insertMessage(SysVerification sysVerification) {
		sysVerificationDao.insertMessage(sysVerification);
	}

	public void updateMessage(SysVerification message) {
		sysVerificationDao.updateMessage(message);
	}

}
