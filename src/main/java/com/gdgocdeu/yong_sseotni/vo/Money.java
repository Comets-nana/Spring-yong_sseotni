package com.gdgocdeu.yong_sseotni.vo;

import java.math.BigDecimal;

public class Money {
	
	private int money_idx=0;
	private int user_idx=0;
	private String money_when=null;
	private String money_type=null;
	private String money_where=null;
	private int money_in=0;
	private int money_out=0;
	private String created_date=null;
	private String del_ny=null;
	
	// 월별 수입/지출 총액
	private BigDecimal totalMoneyIn = BigDecimal.ZERO;
    private BigDecimal totalMoneyOut = BigDecimal.ZERO;
	
	
	
	public int getMoney_idx() {
		return money_idx;
	}
	public void setMoney_idx(int money_idx) {
		this.money_idx = money_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getMoney_when() {
		return money_when;
	}
	public void setMoney_when(String money_when) {
		this.money_when = money_when;
	}
	public String getMoney_type() {
		return money_type;
	}
	public void setMoney_type(String money_type) {
		this.money_type = money_type;
	}
	public String getMoney_where() {
		return money_where;
	}
	public void setMoney_where(String money_where) {
		this.money_where = money_where;
	}
	public int getMoney_in() {
		return money_in;
	}
	public void setMoney_in(int money_in) {
		this.money_in = money_in;
	}
	public int getMoney_out() {
		return money_out;
	}
	public void setMoney_out(int money_out) {
		this.money_out = money_out;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getDel_ny() {
		return del_ny;
	}
	public void setDel_ny(String del_ny) {
		this.del_ny = del_ny;
	}
	public BigDecimal getTotalMoneyIn() {
		return totalMoneyIn;
	}
	public void setTotalMoneyIn(BigDecimal totalMoneyIn) {
		this.totalMoneyIn = totalMoneyIn;
	}
	public BigDecimal getTotalMoneyOut() {
		return totalMoneyOut;
	}
	public void setTotalMoneyOut(BigDecimal totalMoneyOut) {
		this.totalMoneyOut = totalMoneyOut;
	}
	
}
