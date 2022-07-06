package com.project.billyus.controller;

import java.util.ArrayList;
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
import com.project.billyus.service.GoodsService;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.GoodsImgFileVO;
import com.project.billyus.vo.PagingVO;
import com.project.billyus.vo.RegistCtgVO;

//---------------------------------------------------------------------------------
// 상품 컨트롤러 GoodsController
//---------------------------------------------------------------------------------
@Controller(value="goodsController")
@RequestMapping(value="/goods/")
public class GoodsController {

	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@Autowired
	GoodsService goodsService;
	
//------------------------------------- 페이지 맵핑 .GO Page --------------------------------------------------
	
	//---------------------------------------------------------------------------------
	// 물건등록 페이지 매핑 및 매핑시 대분류 카테고리 가져오기
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/registerForm.go", method = RequestMethod.GET)
	public ModelAndView registerForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController searchListGetAjax Start");
		
		List<RegistCtgVO> ctgList = null;
		ctgList = goodsService.listCtg();
		System.out.println(ctgList);
		
		ModelAndView mav = new ModelAndView("/goods/registerForm");
		mav.addObject("ctgList", ctgList);
		
		logger.info("GoodsController searchListGetAjax End");
		
		return mav;
	}
		
	//---------------------------------------------------------------------------------
	// 제품 누르면 제품 상세 페이지 
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/goodsById.go", method = RequestMethod.GET)
	public ModelAndView goodsByIdForm(@RequestParam("goods_code") int goods_code ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 상세페이지 조회시 조회수 증가
		int result = goodsService.goodsViewCount(goods_code);
		System.out.println(result);
		
		
		GoodsByIdVO goodsByIdVO = goodsService.selectGoodsByIdBoard(goods_code);
		RegistCtgVO getGoodsCtgNM 	= goodsService.getGoodsCtgId(goods_code);
		
		System.out.println(goodsByIdVO);
		System.out.println(getGoodsCtgNM);
		
		ModelAndView mav = new ModelAndView("/goods/goodsById");
		mav.addObject("goodsByIdVO", 	goodsByIdVO);
		mav.addObject("getGoodsCtgNM", 	getGoodsCtgNM);
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 제품 리스트 게시판 페이지
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/goodsBoard.go", method = RequestMethod.GET)
	public ModelAndView goodsBoardSearch(@ModelAttribute("vo") PagingVO vo,
			 HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 검색한 상품 게시판 총 개수를 불러온다
		int	total = goodsService.getTotalRow(vo);
		
		// 검색 데이터가 있으면
		System.out.println("searchValue=" + vo.getSearchValue());
		System.out.println("total = " + total);
		System.out.println("nowPage = " +  vo.getNowPage());
		
		// 게시판의 번호들을 계산한다.
		vo = new PagingVO(total, 
				(vo.getNowPage() == 0 ? 1 : vo.getNowPage() ), vo.getSearchValue());

		System.out.println("start = " + vo.getStart());
		System.out.println("last = " + vo.getLastPage());
		System.out.println("END = " + vo.getEnd());
		System.out.println("searchValue2=" + vo.getSearchValue());
		
		// 검색 데이터에 따른 리스트를 불러온다.
		List<GoodsByIdVO> goodsList = goodsService.selectGoodsBoaedList(vo);
		System.out.println("AllGoods = " + goodsList);
		
		// 데이터를 mav에 넣는다.
		ModelAndView mav = new ModelAndView("/goods/goodsBoard");

		mav.addObject("goodsList", goodsList);
		mav.addObject("paging", vo);
	
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
//------------------------------------- 페이지 맵핑 .GO Page - END --------------------------------------------------

//------------------------------------- 페이지 기능 맵핑 .do Page --------------------------------------------------

	//-------------------- ajax - Start ---------------------------
	
	//-----------------------------------------------------------------------------------
	// 중분류 칸에 카테고리 가져오기 ajax
	//-----------------------------------------------------------------------------------
	@RequestMapping(value = "/getCategoryM.do", method = RequestMethod.GET)
	public @ResponseBody List<RegistCtgVO> getCategoryAjax(String category_NM, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.info("GoodsController getCategoryAjax Start");
		System.out.println(category_NM);
		List<RegistCtgVO> searchList = null;
		searchList = goodsService.getCategoryM(category_NM);
		System.out.println(searchList);
		
		logger.info("GoodsController getCategoryAjax End");
		
		return searchList;
		
	}
	
	//-----------------------------------------------------------------------------------
	// 소분류 칸에 카테고리 가져오기 ajax
	//-----------------------------------------------------------------------------------
	@RequestMapping(value = "/getCategoryS.do", method = RequestMethod.GET)
	public @ResponseBody List<RegistCtgVO> getSmallCategoryAjax(String category_NM, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.info("GoodsController getSmallCategoryAjax Start");
		System.out.println(category_NM);
		List<RegistCtgVO> searchList = null; 
		searchList = goodsService.getCategoryS(category_NM);
		
		logger.info("GoodsController getSmallCategoryAjax End");
		
		return searchList;
	}
		
	//-------------------- ajax - END -----------------------------
	
	//-------------------- .do 컨트롤러 ---------------------------
	
	//---------------------------------------------------------------------------------
	// 상품 게시판, 이미지 등록하기
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/registeredGoodsArr.do", method=RequestMethod.POST)
	public ModelAndView registeredGoodsArr(@ModelAttribute("goodsByIdVO") GoodsByIdVO goodsByIdVO, @RequestParam("imageRegist") MultipartFile[] imageRegist, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController registeredGoodsArr End");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		System.out.println(goodsByIdVO);
		goodsByIdVO.setUser_ID(user_ID);
		
		ModelAndView mav = new ModelAndView();
		int result = 0;
		
		// 받아온 파일 배열을 리스트에 넣어서 Service에 보냄
		if (imageRegist != null) {
			// 파일리스트와 게시판 데이터를 보냄
			FileUtils fileUtils = new FileUtils();
			
			// 상품 게시판을 먼저 등록한다.
			result = goodsService.insertGoodsBoard(goodsByIdVO);
			System.out.println("GoodsBoard result = " + result);
			
			// 아이디에 맞는 최근 등록 goods_code를 가져온다.
			int goods_code = goodsService.selectRecentGoodsCode(user_ID);
			System.out.println(goods_code);
			
			// 파일유틸을 실행하여 파일 정보 리스트를 만든다.
			List<GoodsImgFileVO> goodsImgFileVOList = fileUtils.parseFileInfo(uploadPath, user_ID, goods_code, imageRegist);
			System.out.println(goodsImgFileVOList);
			
			// 그 정보를 DB에 저장한다.
			result = goodsService.insertGoodsImgFile(goodsImgFileVOList);
			System.out.println("GoodsImgFile result = " + result);
			
		} else {
			// 파일이 없다면 게시판만 작성하면 게시판 데이터만 들어감
			System.out.println(imageRegist);
			result = goodsService.insertGoodsBoard(goodsByIdVO);
			
		}
		mav.setViewName("redirect:/goods/goodsBoard.go");
		
		logger.info("GoodsController registeredGoodsArr End");
		
		return mav;
	}
	
	//-------------------- .do 컨트롤러 - END -----------------------
	
//------------------------------------- 페이지 기능 맵핑 .do Page - END --------------------------------------------------
	
	
	
	
}
