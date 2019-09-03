package com.drimsys.dto;

public class ProductVO {

	private String product_id;
	private String product_name;
	private int product_price;
	private int product_tot;
	private int product_available;
	private int user_select_quantity;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_tot() {
		return product_tot;
	}

	public void setProduct_tot(int product_tot) {
		this.product_tot = product_tot;
	}

	public int getProduct_available() {
		return product_available;
	}

	public void setProduct_available(int product_available) {
		this.product_available = product_available;
	}

	public int getUser_select_quantity() {
		return user_select_quantity;
	}

	public void setUser_select_quantity(int user_select_quantity) {
		this.user_select_quantity = user_select_quantity;
	}
	
}
