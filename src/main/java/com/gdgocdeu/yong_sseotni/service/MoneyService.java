package com.gdgocdeu.yong_sseotni.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdgocdeu.yong_sseotni.dao.MoneyDao;
import com.gdgocdeu.yong_sseotni.vo.Money;

@Service
public class MoneyService {

	@Autowired
	MoneyDao moneyDao;
	
	public Map<String, BigDecimal> getMonthlyTotal(int user_idx, int year, int month) {
		Map<String, BigDecimal> totals = moneyDao.getMonthlyTotal(user_idx, year, month);
		
		// 계산할 데이터가 없을 때 각각 0원으로 표시
        if (totals == null) {
            totals = new HashMap<>();
            totals.put("totalMoneyIn", BigDecimal.ZERO);
            totals.put("totalMoneyOut", BigDecimal.ZERO);
        }
        
        return totals;
    }
	
	public int save(Money m) {
		return moneyDao.save(m);
	}
	
}
