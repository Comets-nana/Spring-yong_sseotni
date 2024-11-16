package com.gdgocdeu.yong_sseotni.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gdgocdeu.yong_sseotni.dao.MoneyDao;
import com.gdgocdeu.yong_sseotni.vo.DailyMoney;
import com.gdgocdeu.yong_sseotni.vo.Money;

@Service
public class MoneyService {

	@Autowired
	MoneyDao moneyDao;
	
	public Map<String, BigDecimal> getDailyTotal(int user_idx, int year, int month, int day) {
		Map<String, BigDecimal> dailyTotal = moneyDao.getDailyTotal(user_idx, year, month, day);
		
		// 계산할 데이터가 없을 때 각각 0원으로 표시
        if (dailyTotal == null) {
        	dailyTotal = new HashMap<>();
        	dailyTotal.put("dailyMoneyIn", BigDecimal.ZERO);
        	dailyTotal.put("dailyMoneyOut", BigDecimal.ZERO);
        }
        
        return dailyTotal;
	}
	
	public List<DailyMoney> getDailyMoneyList(int user_idx, int year, int month, int day) {
		List<DailyMoney> dailyList = moneyDao.getDailyMoneyList(user_idx, year, month, day);
		
		// 불러올 일일 수입/지출 데이터가 없을 때
		if (dailyList == null || dailyList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		return dailyList;
	}
	
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
