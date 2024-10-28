package com.gdgocdeu.yong_sseotni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdgocdeu.yong_sseotni.dao.MoneyDao;
import com.gdgocdeu.yong_sseotni.vo.Money;

@Service
public class MoneyService {

	@Autowired
	MoneyDao moneyDao;
	
	public int save(Money m) {
		return moneyDao.save(m);
	}
	
}
