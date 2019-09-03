package com.drimsys.dto;

public class UserVO {
	 
    private String user_id;
    private String user_pw;
    private String user_name;
    private int user_using;
    private int user_time;
    private String user_email;
    private int user_select_quantity;
    
    private String join_sales_product_name;
    private int join_sales_product_quantity;
    private int join_sales_product_price;
    private String join_sales_date;
    
    private ProductVO productVO;
    private User_ProductVO ucVO;
    
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
	public String getJoin_sales_product_name() {
		return join_sales_product_name;
	}
	public void setJoin_sales_product_name(String join_sales_product_name) {
		this.join_sales_product_name = join_sales_product_name;
	}
	public int getJoin_sales_product_quantity() {
		return join_sales_product_quantity;
	}
	public void setJoin_sales_product_quantity(int join_sales_product_quantity) {
		this.join_sales_product_quantity = join_sales_product_quantity;
	}
	public int getJoin_sales_product_price() {
		return join_sales_product_price;
	}
	public void setJoin_sales_product_price(int join_sales_product_price) {
		this.join_sales_product_price = join_sales_product_price;
	}
	public String getJoin_sales_date() {
		return join_sales_date;
	}
	public void setJoin_sales_date(String join_sales_date) {
		this.join_sales_date = join_sales_date;
	}
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	public User_ProductVO getUcVO() {
		return ucVO;
	}
	public void setUcVO(User_ProductVO ucVO) {
		this.ucVO = ucVO;
	}
	
	
}

