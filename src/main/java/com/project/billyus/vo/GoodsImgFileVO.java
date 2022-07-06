package com.project.billyus.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;


//--------------------------------------------------
// GoodsImgFileVO
//--------------------------------------------------
@Component(value = "goodsImgFileVO")
public class GoodsImgFileVO {

	//--------------------------------------------------
	// 선언부
	//--------------------------------------------------
	private int 		file_num;
	private int 		goods_code;
	private String 		origin_file_name;
	private String 		stored_file_name;
	private int 		stored_thumbNail;
	private long 		file_size;
	private Date 		create_date;
	private int 		delete_check;
	
	//--------------------------------------------------
	// get set
	//--------------------------------------------------
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public int getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public String getOrigin_file_name() {
		return origin_file_name;
	}
	public void setOrigin_file_name(String origin_file_name) {
		this.origin_file_name = origin_file_name;
	}
	public String getStored_file_name() {
		return stored_file_name;
	}
	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}
	public int getStored_thumbNail() {
		return stored_thumbNail;
	}
	public void setStored_thumbNail(int stored_thumbNail) {
		this.stored_thumbNail = stored_thumbNail;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public int getDelete_check() {
		return delete_check;
	}
	public void setDelete_check(int delete_check) {
		this.delete_check = delete_check;
	}
	
	//--------------------------------------------------
	// toString
	//--------------------------------------------------
	@Override
	public String toString() {
		return "GoodsImgFileVO [file_num=" + file_num + ", goods_code=" + goods_code + ", origin_file_name="
				+ origin_file_name + ", stored_file_name=" + stored_file_name + ", stored_thumbNail=" + stored_thumbNail
				+ ", file_size=" + file_size + ", create_date=" + create_date + ", delete_check=" + delete_check + "]";
	}
	
	
	
	
	
}
