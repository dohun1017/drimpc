package com.drimsys.dto;

public class User_ProductVO {
	 
	private String user_id;
	private String product_id;
	private int product_quantity;
	private String date;
	private String min_date;
	private String max_date;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMin_date() {
		return min_date;
	}
	public void setMin_date(String min_date) {
		this.min_date = min_date;
	}
	public String getMax_date() {
		return max_date;
	}
	public void setMax_date(String max_date) {
		this.max_date = max_date;
	}
	
	
}

