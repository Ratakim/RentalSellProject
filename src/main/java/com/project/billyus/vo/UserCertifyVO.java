package com.project.billyus.vo;

public class UserCertifyVO {
	
	private int no;
	private String card_No;
	private String id;
	private String account;
	private String money;
    private String bank;
    private String cvc;
    
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCard_No() {
		return card_No;
	}
	public void setCard_No(String card_No) {
		this.card_No = card_No;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	@Override
	public String toString() {
		return "UserCertifyVO [no=" + no + ", card_No=" + card_No + ", id=" + id + ", account=" + account + ", money="
				+ money + ", bank=" + bank + ", cvc=" + cvc + "]";
	}
	
    
}
	
	
	
	
	


