package com.project.billyus.vo;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Component(value="reserveVO")
public class ReserveVO {

	
	private int		reserve_NUM;
	private String	user_ID;
	private int		goods_code;
	private int		total_price;
	private String	borrow_period_start;
	private String	borrow_period_end;
	private Date	create_date;
	private String	transaction_status;
	private List<GoodsByIdVO> reserveList;
	
	public List<GoodsByIdVO> getReserveList() {
		return reserveList;
	}
	public void setReserveList(List<GoodsByIdVO> reserveList) {
		this.reserveList = reserveList;
	}
	public String getTransaction_status() {
		return transaction_status;
	}
	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}
	public int getReserve_NUM() {
		return reserve_NUM;
	}
	public void setReserve_NUM(int reserve_NUM) {
		this.reserve_NUM = reserve_NUM;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public int getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getBorrow_period_start() {
		return borrow_period_start;
	}
	public void setBorrow_period_start(String borrow_period_start) {
		this.borrow_period_start = borrow_period_start;
	}
	public String getBorrow_period_end() {
		return borrow_period_end;
	}
	public void setBorrow_period_end(String borrow_period_end) {
		this.borrow_period_end = borrow_period_end;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "ReserveVO [reserve_NUM=" + reserve_NUM + ", user_ID=" + user_ID + ", goods_code=" + goods_code
				+ ", total_price=" + total_price + ", borrow_period_start=" + borrow_period_start
				+ ", borrow_period_end=" + borrow_period_end + ", create_date=" + create_date + ", transaction_status="
				+ transaction_status + ", reserveList=" + reserveList + "]";
	}





}
