package com.project.billyus.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.billyus.service.TransactionService;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.RegistCtgVO;
import com.project.billyus.vo.ReserveVO;

//---------------------------------------------------------------------------------
//거래 및 예약 컨트롤러 TransactionController
//---------------------------------------------------------------------------------
@Controller(value="transactionController")
@RequestMapping(value="/transaction/")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
//------------------------------------- 페이지 기능 맵핑 .do Page --------------------------------------------------
	//---------------------------------------------------------------------------------
	//	상품 예약하기 등록
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/reservation.do")
	public ModelAndView insertReservation( @ModelAttribute("Reserve") ReserveVO reserveVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("TransactionController insertReservation Start");
		
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		System.out.println(reserveVO);
		reserveVO.setUser_ID(user_ID);
		
		int result = transactionService.insertReservation(reserveVO);
		ModelAndView mav = new ModelAndView();
		
		logger.info("TransactionController insertReservation End");
		if(result ==0) {
			mav.addObject("", false);
			mav.setViewName("/transaction/");
		} else {
			mav.addObject("", true);
			mav.setViewName("redirect:/user/myPageLendingList.go?myCondition=빌려주기%20내역");
		}
		
		
		return mav;
	}
	
//--------------------- 판매자 Start ---------------------------------------------
	
	//---------------------------------------------------------------------------------
	//	ajax // (판매자 등록된 상품 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/lendingRegist.do", method = RequestMethod.GET)
	public ModelAndView registedBtnAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		ModelAndView mav = new ModelAndView("/main/user/myPage/myPageAjax/myPageLendingRegist");
		List<GoodsByIdVO> goodsList = transactionService.selectSearchGoodsList(user_ID);
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	//---------------------------------------------------------------------------------
	//	ajax // (판매자 예약 승인중 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/lendingRequest.do", method = RequestMethod.GET)
	public ModelAndView requestedBtnAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		ModelAndView mav = new ModelAndView("/main/user/myPage/myPageAjax/myPageLendingRequest");
		List<GoodsByIdVO> goodsList = transactionService.selectSearchReserveList(user_ID);
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	//	ajax // (판매자 빌려주기 진행중 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/lendingProceed.do", method = RequestMethod.GET)
	public ModelAndView proceedBtnAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		ModelAndView mav = new ModelAndView("/main/user/myPage/myPageAjax/myPageLendingProceed");
		List<GoodsByIdVO> goodsList = transactionService.selectProceedLendingList(user_ID);
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	//	ajax // (판매자 종료된 거래 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/lendingEndTran.do", method = RequestMethod.GET)
	public ModelAndView endTranBtnAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		ModelAndView mav = new ModelAndView("/main/user/myPage/myPageAjax/myPageEndTransact");
		List<GoodsByIdVO> goodsList = transactionService.selectEndLendingList(user_ID);
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
//--------------------- 판매자 END ---------------------------------------------
	
//--------------------- 구매자 Start ---------------------------------------------
	//---------------------------------------------------------------------------------
	//	ajax // (구매자 예약 승인중 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/borrowRequest.do", method = RequestMethod.GET)
	public ModelAndView requestedBtnBorrowAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		ModelAndView mav = new ModelAndView("/main/user/myPage/myPageAjax/myPageBorrowRequest");
		List<GoodsByIdVO> goodsList = transactionService.selectSearchReserveBorrowList(user_ID);
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	//	ajax // (구매자 빌려주기 진행중 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/borrowProceed.do", method = RequestMethod.GET)
	public ModelAndView proceedBtnBorrowAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		ModelAndView mav = new ModelAndView("/main/user/myPage/myPageAjax/myPageBorrowProceed");
		List<GoodsByIdVO> goodsList = transactionService.selectProceedBorrowList(user_ID);
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	//	ajax // (구매자 종료된 거래 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/borrowEndTran.do", method = RequestMethod.GET)
	public ModelAndView endTranBorrowBtnAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		ModelAndView mav = new ModelAndView("/main/user/myPage/myPageAjax/myPageEndTransactBorrow");
		List<GoodsByIdVO> goodsList = transactionService.selectEndBorrowList(user_ID);
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
//--------------------- 구매자 End ---------------------------------------------
	
//------------------------------------------------------------------------
// 거래 현황 관련 버튼
//------------------------------------------------------------------------
	//---------------------------------------------------------------------------------
	//	ajax // (예약 승인 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/approveReserve.do", method = RequestMethod.GET)
	public ModelAndView approveBtnAjax(@ModelAttribute("goodsByIdVO") GoodsByIdVO goodsByIdVO, int reserve_NUM,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		System.out.println(goodsByIdVO);
		goodsByIdVO.setUser_ID(user_ID);
		
		
		int result = transactionService.approveReserve(reserve_NUM);
		List<GoodsByIdVO> goodsList = transactionService.selectSearchGoodsList(user_ID);
		
		ModelAndView mav = new ModelAndView("redirect:/user/myPageLendingList.go?myCondition=빌려주기%20내역");
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	//---------------------------------------------------------------------------------
	//	ajax // (입금하기 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/deposit.do", method = RequestMethod.GET)
	public ModelAndView depositBtnAjax(@ModelAttribute("goodsByIdVO") GoodsByIdVO goodsByIdVO, int reserve_NUM,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		System.out.println(goodsByIdVO);
		goodsByIdVO.setUser_ID(user_ID);
		
		
		int result = transactionService.deposit(reserve_NUM);
		List<GoodsByIdVO> goodsList = transactionService.selectSearchGoodsList(user_ID);
		
		ModelAndView mav = new ModelAndView("redirect:/user/myPageBorrowList.go?myCondition=빌리기%20내역");
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
		
	//---------------------------------------------------------------------------------
	//	ajax // (물품 인수 확인 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/takeOver.do", method = RequestMethod.GET)
	public ModelAndView takeOverBtnAjax(@ModelAttribute("goodsByIdVO") GoodsByIdVO goodsByIdVO, int reserve_NUM,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		System.out.println(goodsByIdVO);
		goodsByIdVO.setUser_ID(user_ID);
		
		
		int result = transactionService.takeOver(reserve_NUM);
		List<GoodsByIdVO> goodsList = transactionService.selectSearchGoodsList(user_ID);
		
		ModelAndView mav = new ModelAndView("redirect:/user/myPageBorrowList.go?myCondition=빌리기%20내역");
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	//	ajax // (물품 반납 확인 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/returned.do", method = RequestMethod.GET)
	public ModelAndView returnedBtnAjax(@ModelAttribute("goodsByIdVO") GoodsByIdVO goodsByIdVO, int reserve_NUM,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		System.out.println(goodsByIdVO);
		goodsByIdVO.setUser_ID(user_ID);
		
		
		int result = transactionService.returned(reserve_NUM);
		List<GoodsByIdVO> goodsList = transactionService.selectSearchGoodsList(user_ID);
		
		ModelAndView mav = new ModelAndView("redirect:/user/myPageLendingList.go?myCondition=빌려주기%20내역");
		mav.addObject("goodsList", goodsList);
		System.out.println(goodsList);
		
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	//	ajax // (예약 취소 버튼)
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/cancelReserve.do", method = RequestMethod.GET)
	public ModelAndView cancelReserveBtnAjax(@ModelAttribute("reserveVO") ReserveVO reserveVO, int reserve_NUM,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("GoodsController goodsByIdForm Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		System.out.println(reserveVO);
		reserveVO.setUser_ID(user_ID);
		
		
		int result = transactionService.cancelReserve(reserve_NUM);
		
		
		ModelAndView mav = new ModelAndView("redirect:/user/myPageLendingList.go?myCondition=빌려주기%20내역");
		
		
		
		logger.info("GoodsController goodsByIdForm End");
		
		return mav;
	}
}
