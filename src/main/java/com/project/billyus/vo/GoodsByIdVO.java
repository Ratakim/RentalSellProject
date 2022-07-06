package com.project.billyus.vo;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.billyus.common.Time;

@Component(value="goodsByIdVO")
public class GoodsByIdVO {
	private	int 		goods_code;
	private int			category_ID;
	private String		user_ID;
	private	String		deal_region;
	private String		goods_title;
	private	String		goods_desc;
	private	int			goods_price;
	private String		goods_price_comma;
	private	String		create_date;
	private	int			goods_hit;
	private List<GoodsImgFileVO> goodsFileList;
	private List<ReserveVO> reserveList;
	
	//--------------------------------------------
	// get, set
	//--------------------------------------------
	public int getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public int getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(int category_ID) {
		this.category_ID = category_ID;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getGoods_title() {
		return goods_title;
	}
	public void setGoods_title(String goods_title) {
		this.goods_title = goods_title;
	}
	public String getDeal_region() {
		return deal_region;
	}
	public void setDeal_region(String deal_region) {
		this.deal_region = deal_region;
	}
	public String getGoods_desc() {
		return goods_desc;
	}
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	public int getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = Time.calculateTime(create_date);
	}
	public int getGoods_hit() {
		return goods_hit;
	}
	public void setGoods_hit(int goods_hit) {
		this.goods_hit = goods_hit;
	}
	public String getGoods_price_comma() {
		return goods_price_comma;
	}
	public void setGoods_price_comma(String goods_price_comma) {
		this.goods_price_comma = goods_price_comma;
	}
	public List<GoodsImgFileVO> getGoodsFileList() {
		return goodsFileList;
	}
	public void setGoodsFileList(List<GoodsImgFileVO> goodsFileList) {
		this.goodsFileList = goodsFileList;
	}
	public List<ReserveVO> getReserveList() {
		return reserveList;
	}
	public void setReserveList(List<ReserveVO> reserveList) {
		this.reserveList = reserveList;
	}
	//--------------------------------------------
	// toString
	//--------------------------------------------
	@Override
	public String toString() {
		return "GoodsByIdVO [goods_code=" + goods_code + ", category_ID=" + category_ID + ", user_ID=" + user_ID
				+ ", deal_region=" + deal_region + ", goods_title=" + goods_title + ", goods_desc=" + goods_desc
				+ ", goods_price=" + goods_price + ", goods_price_comma=" + goods_price_comma + ", create_date="
				+ create_date + ", goods_hit=" + goods_hit + ", goodsFileList=" + goodsFileList + ", reserveList="
				+ reserveList + "]";
	}
	
}
