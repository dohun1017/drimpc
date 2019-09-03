package com.drimsys.dto;

public class UserVO {
	 
    private String user_id;
    private String user_pw;
    private String user_name;
    private int user_using;
    private int user_time;
    private String user_email;
    private int user_select_quantity;
    private ComputerVO cVO;
    
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_using() {
		return user_using;
	}
	public void setUser_using(int user_using) {
		this.user_using = user_using;
	}
	public int getUser_time() {
		return user_time;
	}
	public void setUser_time(int user_time) {
		this.user_time = user_time;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getUser_select_quantity() {
		return user_select_quantity;
	}
	public void setUser_select_quantity(int user_select_quantity) {
		this.user_select_quantity = user_select_quantity;
	}
	public ComputerVO getCVO() {
		return cVO;
	}
	public void setCVO(ComputerVO cVO) {
		this.cVO = cVO;
	}
	
	
}

