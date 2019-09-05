package com.drimsys.dto;

public class ProductVO {

	private String product_id;
	private String product_name;
	private int product_price;
	private int product_tot;
	private int product_available;
	private int user_select_quantity;

	private int join_product_all_price;
	private int join_product_all_quantity;
	private int join_date_price;
	
	private String start_date;
	private String end_date;
	
	private User_ProductVO ucVO;
	
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

	public int getJoin_product_all_price() {
		return join_product_all_price;
	}

	public void setJoin_product_all_price(int join_product_all_price) {
		this.join_product_all_price = join_product_all_price;
	}

	public int getJoin_product_all_quantity() {
		return join_product_all_quantity;
	}

	public void setJoin_product_all_quantity(int join_product_all_quantity) {
		this.join_product_all_quantity = join_product_all_quantity;
	}

	public int getJoin_date_price() {
		return join_date_price;
	}

	public void setJoin_date_price(int join_date_price) {
		this.join_date_price = join_date_price;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public User_ProductVO getUcVO() {
		return ucVO;
	}

	public void setUcVO(User_ProductVO ucVO) {
		this.ucVO = ucVO;
	}
	
	
}
