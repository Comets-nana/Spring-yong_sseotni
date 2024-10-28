package com.gdgocdeu.yong_sseotni.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdgocdeu.yong_sseotni.vo.Money;

@Repository
public class MoneyDao {

	@Autowired
	SqlSession s;
	
	public int save(Money m) {
		return s.insert("MoneyMapper.save", m);
	}
	
	
}
