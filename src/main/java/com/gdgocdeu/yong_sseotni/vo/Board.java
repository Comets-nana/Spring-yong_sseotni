package com.gdgocdeu.yong_sseotni.vo;

public class Board {

	private int board_idx=0;
	private int user_idx=0;
	private String board_title=null;
	private String board_content=null;
	private String created_date=null;
	private String modified_date=null;
	private String del_ny=null;
	
	
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getDel_ny() {
		return del_ny;
	}
	public void setDel_ny(String del_ny) {
		this.del_ny = del_ny;
	}
	
}
