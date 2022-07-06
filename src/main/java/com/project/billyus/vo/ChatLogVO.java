package com.project.billyus.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component(value="chatLogVO")
public class ChatLogVO {
	
	private int 	log_NUM;
	private String 	borrower_ID;
	private String 	lender_ID;  
	private Date 	create_date;
	private Date 	connect_date;
	
    
}
