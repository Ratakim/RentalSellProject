package com.project.billyus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.billyus.dao.ManagerDAO;
import com.project.billyus.service.ManagerService;
import com.project.billyus.vo.ManagerVO;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {
	private static final Logger logger= LoggerFactory.getLogger(ManagerController.class);

	@Autowired
	ManagerDAO managerDAO;
	@Autowired
	ManagerService managerService;
	
	//매니저 회원가입  가입페이지
	@RequestMapping(value="/managerUpPage.go")
	public ModelAndView managerUpPage() {
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("/manager/managerUpPage");
		
		
		return mav;
	}
	
	//매니저 회원가입 실행
	@RequestMapping(value="/managerUpPage.do")
	public ModelAndView insertManager(@ModelAttribute("manager") ManagerVO manager, HttpServletRequest request) throws Exception {
		
		System.out.println(manager);
		//회원가입 실행된값을 리절트값에 받아온다.
		int result=0;
		result= managerDAO.insertManager(manager);
		
		//result로 받아온값을 mav에 넣을때 값이있으면 성공 없으면 실패
		ModelAndView mav = new ModelAndView();
		
		if(result==0) {
			mav.addObject("signUpdone", false);
			//결과값에따라 보여줄 뷰 맵핑
			mav.setViewName("/manager/managerUpPage.go");
		}else {
			mav.addObject("signUpdone", true);
			//결과값에따라 보여줄 뷰 맵핑
			mav.setViewName("redirect:/manager/managerLogin");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/managerLogin")
	public ModelAndView managerLogin() {
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("/manager/managerLogin");
		
		
		return mav;
	}
	
	// MemberList
	@RequestMapping(value="/managerMember.go")
	public String managerMember(Model model, HttpServletRequest request, HttpServletResponse response)throws DataAccessException {
		
		List<ManagerVO> memberListc = managerService.selectMember();
		System.out.println(memberListc);
		model.addAttribute("memberListc", memberListc);
		
		return "/main/manager/managerMember";
	}
	
	
}