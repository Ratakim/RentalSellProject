package com.project.billyus.controller;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMail {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void mailSendWithUserKey(String email, String id, HttpServletRequest request) {
			
		
		MimeMessage mail = mailSender.createMimeMessage();
		
		// 본문
		String htmlStr = "<h2>안녕하세요 MS :p 민수르~ 입니다!</h2><br><br>" 
				+ "<h3>"+id+"님</h3>" + "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://localhost:8083/home'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
	
	
		try {
			// 제목
			mail.setSubject("[본인인증] MS :p "+id+"님의 인증메일입니다", "utf-8");
			// 본문
			mail.setText(htmlStr, "utf-8", "html");
			//InternetAddress(보낼 이메일)
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
