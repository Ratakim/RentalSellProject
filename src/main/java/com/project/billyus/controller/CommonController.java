package com.project.billyus.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.billyus.common.FileUtils;
import com.project.billyus.dao.CommonDAO;
import com.project.billyus.service.BoardService;
import com.project.billyus.service.CommonService;
import com.project.billyus.service.GoodsService;
import com.project.billyus.service.TransactionService;
import com.project.billyus.service.UserService;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.GoodsImgFileVO;
import com.project.billyus.vo.RegistCtgVO;

import net.coobird.thumbnailator.Thumbnails;

//---------------------------------------------------------------------------------
// 메인 컨트롤러 CommonController
//---------------------------------------------------------------------------------
@Controller(value="commonController")
@RequestMapping(value="/common/")
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	//---------------------------------------------------------------------------------
	// 선언부
	//---------------------------------------------------------------------------------
	@Resource(name="uploadPath")
	private String uploadPath;
	@Autowired
	GoodsService goodsService;
	@Autowired
	UserService userService;
	@Autowired
	CommonService commonService;
	@Autowired
	BoardService boardService;
	@Autowired
	RegistCtgVO registCtgVO;

	
//------------------------------------- 페이지 맵핑 .GO Page --------------------------------------------------
	
	//---------------------------------------------------------------------------------
	// 메인 페이지
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/main.go", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("CommonController main Start");
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/main");
		
		logger.info("CommonController main End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 이용약관 페이지
	//---------------------------------------------------------------------------------   
	@RequestMapping(value="/termsOfUse.go") // 이런 맵핑이 API
	public ModelAndView termsOfUse(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
		logger.info("CommonController termsOfUse Start");
		  
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/main/common/termsOfUse");
		  
		logger.info("CommonController termsOfUse End");
		      
		return mav;
	}
   
	//---------------------------------------------------------------------------------
	// 개인정보처리방침 페이지
	//---------------------------------------------------------------------------------   
	@RequestMapping(value="/perInfo.go") // 이런 맵핑이 API
	public ModelAndView perInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
		logger.info("CommonController perInfo Start");
	  
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/main/common/perInfo");
		  
		logger.info("CommonController perInfo End");
      
		return mav;
   	}
   
	//---------------------------------------------------------------------------------
	// 위치기반서비스 페이지
	//---------------------------------------------------------------------------------   
	@RequestMapping(value="/locService.go") // 이런 맵핑이 API
	public ModelAndView locService(HttpServletRequest request, HttpServletResponse response) throws Exception {
         
		logger.info("CommonController locService Start");
		 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/main/common/locService");
		     
		logger.info("CommonController locService End");
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// socketJsp 페이지 가기 TEST
	//---------------------------------------------------------------------------------
	@RequestMapping("/socketJsp")
	public ModelAndView socketJsp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		ModelAndView mav = new ModelAndView("/common/rentaltalk");
		
		return mav;
	}
	
//------------------------------------- 페이지 맵핑 .GO Page - END --------------------------------------------------

//------------------------------------- 페이지 기능 맵핑 .do Page --------------------------------------------------
	
	//---------------------- ajax ---------------------------
	
	//---------------------------------------------------------------------------------
	// 검색 정보 가져오기 ajax
	//---------------------------------------------------------------------------------
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	public @ResponseBody List<RegistCtgVO> searchListGetAjax(@RequestParam("name") String name, @RequestParam("test") String test, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("CommonController searchListGetAjax Start");
		
		System.out.println(test);
		System.out.println(name);
		
		List<RegistCtgVO> searchList = null; 
		searchList = commonService.searchList(name);
		
		logger.info("CommonController searchListGetAjax End");
		
		return searchList;
	}
	
	//-------------------- ajax - END -----------------------
	
	//---------------------------------------------------------------------------------
	// 이미지 썸네일 만들기
	//---------------------------------------------------------------------------------
	@RequestMapping("/thumbnails.do")
	protected void thumbnails(@RequestParam("user_ID") String user_ID, @RequestParam("goods_code") String goods_code, @RequestParam("stored_file_name") String stored_file_name,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OutputStream out = response.getOutputStream();
		
		String filePath  = uploadPath + "\\" + user_ID + "\\" + goods_code + "\\" + stored_file_name;
		System.out.println("CommonController thumbnails.do filePath ==> " + filePath);
		
		File image = new File(filePath);
		// 확장자 분리
		String fileExtension = FileUtils.mkExtension(stored_file_name);
		System.out.println("fileExtension = " + fileExtension);
		
		if (image.exists()) {
			Thumbnails.of(image).scale(1).outputFormat(fileExtension).toOutputStream(out);
		}
		
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
		
	} // End - thumbnails
	
	
	
	
//------------------------------------- 페이지 기능 맵핑 .do Page - END --------------------------------------------------
	
}
