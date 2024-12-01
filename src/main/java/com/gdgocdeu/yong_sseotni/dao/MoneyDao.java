package com.gdgocdeu.yong_sseotni.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdgocdeu.yong_sseotni.vo.DailyMoney;
import com.gdgocdeu.yong_sseotni.vo.Money;
import com.gdgocdeu.yong_sseotni.vo.User;

@Repository
public class MoneyDao {

	@Autowired
	SqlSession s;
	
	public int deleteMoney(Money m) {
		return s.update("MoneyMapper.deleteMoney", m);
	}
	
	public Money findByIdx(int money_idx) {
		return s.selectOne("MoneyMapper.findByIdx", money_idx);
	}
	
	public Map<String, BigDecimal> compareMoney(int user_idx, int currentYear, int currentMonth, int lastYear, int lastMonth) {
		return s.selectOne("compareMoney", Map.of("user_idx", user_idx, "currentYear", currentYear, "currentMonth", currentMonth, "lastYear", lastYear, "lastMonth", lastMonth));
	}
	
	public Map<String, BigDecimal> getDailyTotal(int user_idx, int year, int month, int day) {
		return s.selectOne("getDailyTotal", Map.of("user_idx", user_idx, "year", year, "month", month, "day", day));
	}
	
	public List<DailyMoney> getDailyMoneyList(int user_idx, int year, int month, int day) {
		return s.selectList("getDailyMoneyList", Map.of("user_idx", user_idx, "year", year, "month", month, "day", day));
	}
	
	public Map<String, BigDecimal> getMonthlyTotal(int user_idx, int year, int month) {
		return s.selectOne("getMonthlyTotal", Map.of("user_idx", user_idx, "year", year, "month", month));
	}
	
	public int save(Money m) {
		return s.insert("MoneyMapper.save", m);
	}
	
	
}
