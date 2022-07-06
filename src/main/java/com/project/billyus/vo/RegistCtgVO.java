package com.project.billyus.vo;

import org.springframework.stereotype.Component;

//-------------------------------------------
//대분류 카테고리 정보
//-------------------------------------------
@Component(value="registCtgVO")
public class RegistCtgVO {
	private int 		category_ID;
	private int 		category_LEV;
	private String		category_NM;
	private int			category_detail_LEV;
	private int			category_parent_LEV;
	private int			category_detail_parent_LEV;
	private	char		group_ID;
	
	
	public int getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(int category_ID) {
		this.category_ID = category_ID;
	}
	public int getCategory_LEV() {
		return category_LEV;
	}
	public void setCategory_LEV(int category_LEV) {
		this.category_LEV = category_LEV;
	}
	public String getCategory_NM() {
		return category_NM;
	}
	public void setCategory_NM(String category_NM) {
		this.category_NM = category_NM;
	}
	public int getCategory_detail_LEV() {
		return category_detail_LEV;
	}
	public void setCategory_detail_LEV(int category_detail_LEV) {
		this.category_detail_LEV = category_detail_LEV;
	}
	public int getCategory_parent_LEV() {
		return category_parent_LEV;
	}
	public void setCategory_parent_LEV(int category_parent_LEV) {
		this.category_parent_LEV = category_parent_LEV;
	}
	public int getCategory_detail_parent_LEV() {
		return category_detail_parent_LEV;
	}
	public void setCategory_detail_parent_LEV(int category_detail_parent_LEV) {
		this.category_detail_parent_LEV = category_detail_parent_LEV;
	}
	public char getGroup_ID() {
		return group_ID;
	}
	public void setGroup_ID(char group_ID) {
		this.group_ID = group_ID;
	}
	
	@Override
	public String toString() {
		return "RegistCtgVO [category_ID=" + category_ID + ", category_LEV=" + category_LEV + ", category_NM="
				+ category_NM + ", category_detail_LEV=" + category_detail_LEV + ", category_parent_LEV="
				+ category_parent_LEV + ", category_detail_parent_LEV=" + category_detail_parent_LEV + ", group_ID="
				+ group_ID + "]";
	}
	
	
	
	
}
