package com.project.billyus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.billyus.service.UserService;
import com.project.billyus.vo.UserInfoVO;


//-------------------------------------------------------------------------------------------------
// public class UserFindController extends UserController
//-------------------------------------------------------------------------------------------------
@Controller(value = "userFindController")
@RequestMapping(value="/find")
public class UserFindController {
	
	@Autowired
	UserService userService;
	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(UserFindController.class);
	
	//-------------------------------------------------------------------------------------
	// public ModelAndView findUserId
	//-------------------------------------------------------------------------------------
	@RequestMapping(value="/userInfo", method=RequestMethod.POST)
	public ModelAndView findUserInfo(@ModelAttribute(name="userInfoVO") UserInfoVO userInfoVO, @RequestParam(value="checkType") String checkType, HttpServletRequest request) {
		
		logger.info("UserFindController findUserInfo Start");
		
		System.out.println(userInfoVO);
		// 체크 성공 여부
		String checkResult = "";
		ModelAndView mav = new ModelAndView();
		
		// 이름과 이메일로 정보가 있는지 검사한다.
		UserInfoVO userExists = userService.findUserInfo(userInfoVO);
		System.out.println(userExists);
		
		// 파라미터VO의 이메일 값이 존재한다면
		if (userInfoVO.getEmail() != null) {
			checkResult = checkType;
			
			// 패스워드 찾기 성공시
			if(checkType.equals("pwdSuccess")) {
				checkResult = "pwdUpdate";
				
			// 아이디 찾기
			} else {
				
				// 유저 정보가 존재한다면
				if(userExists != null) {
					checkResult = mailSendWithUserKey(userExists.getEmail(), userExists.getId(), checkType);
				} else {
					checkResult = "noInfo";
				}
			}
			
		} else {
			checkResult = checkType;
		}
		
		// 결과를 저장한다.
		System.out.println("checkResult = " + checkResult);
		mav.addObject("checkResult", checkResult);
		mav.setViewName("/user/userFindInfo");
		
		logger.info("UserFindController findUserInfo End");
		
		return mav;
		
	} // END - public ModelAndView findUserId
	
	//-------------------------------------------------------------------------------------
	// public ModelAndView findUserId
	//-------------------------------------------------------------------------------------
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam("password") String password, @RequestParam("id") String id, HttpServletRequest request) {
		
		logger.info("UserFindController resetPassword Start");
		
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		map.put("password", password);
		map.put("id", id);
		System.out.println(map);
		
		String checkResult = "";
		
		if(password != null && id != null) {
			int result = userService.updateUserPwd(map);
			if(result == 1) {
				checkResult = "resetPwdSuccess";				
			} else {
				checkResult = "resetPwdFail";
			}
		} else {
			checkResult = "resetPwdFail";
		}
		
		mav.addObject("checkResult", checkResult);
		mav.setViewName("/user/userFindInfo");
		
		logger.info("UserFindController resetPassword End");
		
		return mav;
	}

	//-------------------------------------------------------------------------------------
	// public ModelAndView findUserId
	//-------------------------------------------------------------------------------------
	public String mailSendWithUserKey(String email, String checkValue, String checkType) {
		
		logger.info("UserFindController mailSendWithUserKey Start");
		
		MimeMessage mail = mailSender.createMimeMessage();
		
		String htmlStr = "";
		
		if(checkType.equals("id")) {
			htmlStr	= "<h2>안녕하세요</h2><br/>"
					+ "<p>확인되신 " + checkType + "는 " + checkValue + " 입니다.</p>" 
					+ "<p><a href='http://localhost:8083/user/loginForm.go'>로그인 하러가기</a></p>"
					+ "<form action='http://localhost:8083/find/userInfo' method='post'>"
					+ "<input type='hidden' name='checkType' value='pwd'/>"
					+ "<button type='submit' style='background-color: white; border: 0; color: blue;'>비밀번호 찾기</button><br/>"
					+ "</form>"
					+ "<p>(혹시 잘못 전달된 메일이라면 이 이메일을 무시하시고 해당 사이트에 문의하시기 바랍니다.)</p>";
		} else {
			htmlStr	= "<h2>안녕하세요</h2><br/>"
					+ "<p>["+ checkValue +"]님께서 요구하신 비밀번호 찾기입니다.</p>"
					+ "<p>아래의 링크를 누르시면 비밀번호 변경 창으로 이동됩니다.</p>" 
					+ "<form action='http://localhost:8083/find/userInfo' method='post'>"
					+ "<input type='hidden' name='checkType' value='pwdSuccess'/>"
					+ "<input type='hidden' name='id' value='" + checkValue + "'/>"
					+ "<input type='hidden' name='email' value='" + email + "'/>"
					+ "<button type='submit' style='background-color: white; border: 0; color: blue;'>비밀번호 변경하기</button><br/>"
					+ "</form>"
					+ "<p>(혹시 잘못 전달된 메일이라면 이 이메일을 무시하시고 해당 사이트에 문의하시기 바랍니다.)</p>";
		}
		
		try {
			mail.setSubject("BillyUs사이트에서 보낸 [" + checkType + " 찾기] 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
			checkType += "Success";
		} catch (Exception e) {
			e.printStackTrace();
			checkType = "error";
		}
		
		logger.info("UserFindController mailSendWithUserKey End");
		return checkType;
		
	} // END - public ModelAndView findUserId
	
} // END - public class UserFindController extends UserController

