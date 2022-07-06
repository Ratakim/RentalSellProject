package com.project.billyus.controller;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.billyus.service.TransactionService;
import com.project.billyus.service.UserService;
import com.project.billyus.vo.BoardVO;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.UserCertifyVO;
import com.project.billyus.vo.UserInfoVO;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


//---------------------------------------------------------------------------------
// 유저 컨트롤러 UserController
//---------------------------------------------------------------------------------
@Controller(value="userController")
@RequestMapping(value="/user/")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name="uploadPath")
	String uploadPath;
	@Autowired
	UserInfoVO userInfoVO;
	@Autowired
	UserService userService;
	@Autowired
	TransactionService transactionService;
	@Autowired
	private JavaMailSender mailSender;
	
//--------------------------------------- 페이지 맵핑 .GO Page --------------------------------------------------
	
	//---------------------------------------------------------------------------------
	// 로그인 폼
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/loginForm.go")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController loginForm.go Start");
	
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/loginForm");
		
		logger.info("UserController loginForm.go End");
	
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 회원가입 페이지 매핑
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/signUpForm.go", method = RequestMethod.GET)
	public ModelAndView signUpForm() throws Exception {
		
		logger.info("UserController signUpForm Start");
	
		ModelAndView mav = new ModelAndView("/user/userSignUpPage");

		logger.info("UserController signUpForm End");
		
		return mav;
	}
	
	
	
	
	//---------------------------------------------------------------------------------
	// 마이페이지 맵핑
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/myPageMain.go", method = RequestMethod.GET)
	public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController myPage End");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userInfoId");
		
		int resultAccount = userService.selectCountAccount(id);
		int resultCard = userService.selectCountCard(id);
		
		System.out.println(resultAccount + resultCard);
		
		ModelAndView mav = new ModelAndView("/user/myPageMain");
		mav.addObject("resultAccount", resultAccount);
		mav.addObject("resultCard", resultCard);
		
		logger.info("UserController myPage End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// id로 회원 정보 보기
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/userInfo.go")
	public ModelAndView showUserInfo(@ModelAttribute("userInfo") UserInfoVO userInfo, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
		logger.info("UserController showUserInfo Start");
		
		HttpSession session = request.getSession();
		
		String userId =  (String) session.getAttribute("userInfoId");
		System.out.println(userId);
		
		userInfoVO = userService.userInfo(userId);
		System.out.println(userInfoVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/myInfo");
		mav.addObject("userInfo", userInfoVO);
	
		logger.info("UserController showUserInfo End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 빌리기 내역  myPageDisinterestList
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/myPageBorrowList.go", method = RequestMethod.GET)
	public ModelAndView myPageBorrowList(@RequestParam("myCondition") String myCondition,	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController main Start");
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		int requestCount = transactionService.getRequestCountB(user_ID);
		int proceedCount = transactionService.getProceedCountB(user_ID);
		int endedCount = transactionService.getEndedCountB(user_ID);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("requestCount", requestCount);
		mav.addObject("proceedCount", proceedCount);
		mav.addObject("endedCount", endedCount);
		mav.setViewName("/user/myPageBorrowList");
		
		logger.info("UserController main End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 빌려주기 내역  myPageDisinterestList
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/myPageLendingList.go", method = RequestMethod.GET)
	public ModelAndView myPageLendingList(@RequestParam("myCondition") String myCondition,	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController main Start");
		
		
		// 세션 값에서 유저의 아이디를 받아옴
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");		
		System.out.println(user_ID);
		
		int registCount = transactionService.getRegistCount(user_ID);
		int requestCount = transactionService.getRequestCount(user_ID);
		int proceedCount = transactionService.getProceedCount(user_ID);
		int endedCount = transactionService.getEndedCount(user_ID);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/myPageLendingList");
		mav.addObject("registCount", registCount);
		mav.addObject("requestCount", requestCount);
		mav.addObject("proceedCount", proceedCount);
		mav.addObject("endedCount", endedCount);
		
		logger.info("UserController main End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 관심물품  myPageDisinterestList
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/myPageDisinterestList.go", method = RequestMethod.GET)
	public ModelAndView myPageDisinterestList(@RequestParam("myCondition") String myCondition,	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController main Start");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/myPageDisinterestList");
		
		logger.info("UserController main End");
		
		return mav;
	}
	//---------------------------------------------------------------------------------
	// 거래내역 ( Transaction )
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/myPageTransaction.go", method = RequestMethod.GET)
	public ModelAndView transaction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController main Start");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/myPageTransaction");
		
		logger.info("UserController main Start");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 나의 계좌등록 페이지 ( myPageCertify )
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/myPageCertify.go", method = RequestMethod.GET)
	public ModelAndView myPageCertify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController main Start");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/myPageCertify");
		
		logger.info("UserController main End");
		
		return mav;
	}

	// ---------------------------------------------------------------------------------
	// 나의 카드등록 페이지 ( myPageCard )
	// ---------------------------------------------------------------------------------
	@RequestMapping(value ="/myPageCard.go", method = RequestMethod.GET)
	public ModelAndView myPageCard(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("UserController myPageCard.go Start");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/myPageCard");

		logger.info("UserController myPageCard.go End");

		return mav;
	}
	
	// ---------------------------------------------------------------------------------
	// 계좌 등록 post
	// ---------------------------------------------------------------------------------
	@PostMapping("/insertUserAccount.go")
	public ModelAndView insertUserAccount(UserCertifyVO vo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");
		vo.setId(user_ID);

		userService.insertUserAccount(vo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/myPageMain.go");

		return mav;

	}

	// ---------------------------------------------------------------------------------
	// 카드 등록 post
	// ---------------------------------------------------------------------------------
	@PostMapping("/insertUserCard.go")
	public ModelAndView insertUserCard(UserCertifyVO vo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");
		vo.setId(user_ID);

		userService.insertUserCard(vo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/myPageMain.go");

		return mav;

	}

	
	//---------------------------------------------------------------------------------
	// 마일리지 mileage
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/mileage.go", method = RequestMethod.GET)
	public ModelAndView mileage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController mileage Start");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/mileage");
		
		logger.info("UserController mileage End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 마일리지 충전 mileageCharge
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/mileageCharge.go", method = RequestMethod.GET)
	public ModelAndView mileageCharge(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController mileageCharge Start");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/mileageCharge");
		
		logger.info("UserController mileageCharge End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 마일리지 리스트 mileageList
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/mileageList.go", method = RequestMethod.GET)
	public ModelAndView mileageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController mileageList Start");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/mileageList");
		
		logger.info("UserController mileageList End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 마일리지 출금 mileageWithdraw
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/mileageWithdraw.go", method = RequestMethod.GET)
	public ModelAndView mileageWithdraw(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController mileageWithdraw Start");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/mileageWithdraw");
		
		logger.info("UserController mileageWithdraw End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 회원수정 페이지 매핑
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/myPageInfo.go", method = RequestMethod.GET)
	public ModelAndView myPageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController myPageInfo Start");
	
		HttpSession session = request.getSession();
		
		String user_ID = (String) session.getAttribute("userInfoId");
		UserInfoVO userInfoVO = userService.userInfo(user_ID);
		
		ModelAndView mav = new ModelAndView("/user/myPageInfo");
		mav.addObject("userInfo", userInfoVO);
		
		logger.info("UserController myPageInfo End");
		
		return mav;
	}
	
	
	
//------------------------------------- 페이지 맵핑 .GO Page - END --------------------------------------------------
	
//------------------------------------- 페이지 기능 맵핑 .do Page --------------------------------------------------

	//---------------------- ajax ---------------------------
	
	//---------------------------------------------------------------------------------
	// 보안 코드 요청 보내기
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/securityCode.do", method = RequestMethod.POST)
	public @ResponseBody String securityCodeAjax() throws Exception {
		
		logger.info("UserController securityCodeAjax Start");
		
		Random random =	new Random();
		
		int ranNum = 0;			// 랜덤한 1자리가 생성될 변수
		int codeLenth = 6;		// 랜덤한 숫자의 자릿수 변수
		String resultCode = "";	// 6자리 코드가 들어갈 String
		
		for(int i = 0; i < codeLenth; i++) {
			
			ranNum = random.nextInt(9);
			resultCode += "" + ranNum;
		} 
		
		logger.info("UserController securityCodeAjax End");
		
		return resultCode;
	}
	
	//---------------------------------------------------------------------------------
	// 아이디 중복검사
	//---------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public int idCheck(String id) throws Exception {
		
		int result = userService.idCheck(id);
		
		
		return result;
	}
	
	
	//-------------------- ajax - END -----------------------
		
	//-------------------- .do 컨트롤러 ---------------------------
	
	//---------------------------------------------------------------------------------
	// 회원가입 실행
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/insertUser.do")
	public ModelAndView insertUser(@ModelAttribute("UserInfo") UserInfoVO UserInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController insertUser Start");
		
		int result = userService.insertUser(UserInfo);
		System.out.println(result);
		
		ModelAndView mav = new ModelAndView();
		
		if(result == 0) {
			mav.addObject("signUpDone", false);
			mav.setViewName("/user/signUpForm.go");
		} else {
			mav.addObject("signUpDone", true);
			mav.setViewName("redirect:/user/loginForm.go");
		}
		
		logger.info("UserController insertUser End");
		
		return mav;
	}

	// --------------------------------------------------------------------
	// 이메일 인증 발송
	// --------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/signUpForm.do", method = RequestMethod.GET)
	public String send(UserInfoVO userVO, HttpServletRequest request) {

		String email;
		email = userVO.getEmail();

		String key = mailSendWithUserKey(email, userVO.getId(), request);
		// ( ,userVO.getId(), request);

		return key;
	}
		
		//--------------------------------------------------------------------
		// 이메일 발송 내용
		// 함수에 타입이 없이 void만 쓴다면 기능만 실행하고 끝이다.
		// 함수에 타입을 쓰면 return으로 값을 돌려주는 것이다.
		//--------------------------------------------------------------------
		public String mailSendWithUserKey(String email, String id, HttpServletRequest request) {

			MimeMessage mail = mailSender.createMimeMessage();
			//메일 인증코드 발송
			 String key = "" + (int)((Math.random()* (99999 - 10000 + 1)) + 10000);
			
			String htmlStr = "<h2>안녕하세요  !</h2><br><br>" 
					+ "<h3>"+id+"님</h3>" + "<p>[인증번호]"+key+" 인증번호 확인란에 기입해주십시오." 
					+ "<a href='http://localhost:8083/userReg'>인증하기하러가기</a></p>"
					+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		
		
			try {
				mail.setSubject("[본인인증] MS :p "+id+"님의 인증메일입니다", "utf-8");
				mail.setText(htmlStr, "utf-8", "html");
				mail.addRecipient(RecipientType.TO, new InternetAddress(email));
				mailSender.send(mail);
			} catch (Exception e) {
				e.printStackTrace();
				key = "error";
			}
			 
			// 돌려주는 것
			return key;
		}
	
	//---------------------------------------------------------------------------------
	// 회원 로그인
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/userLogin.do", method = RequestMethod.POST)
	public ModelAndView userLogin(@ModelAttribute("customer") UserInfoVO user, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("UserController userLogin Start");
		
		ModelAndView mav = new ModelAndView();
		
		userInfoVO = userService.userInfo(user.getId());
		
		if(userInfoVO == null) { // 회원 정보가 없으면
			rAttr.addAttribute("loginResult", "idDenied");
			mav.setViewName("redirect:/user/loginForm.go");
			
		} else { // 회원 정보가 있으면

			if( userInfoVO.getPwd().equals(user.getPwd()) ) { // 패스워드가 맞으면
				//세션발급
				HttpSession session = request.getSession();
				session.setAttribute("userInfoId", userInfoVO.getId());
				session.setAttribute("loginResult", "access");
				mav.setViewName("redirect:/common/main.go");
				int result = userService.updateUserLastJoin(userInfoVO.getId());
				System.out.println("result");
			} else { // 패스워드가 맞지 않으면 
				rAttr.addAttribute("loginResult", "pwdDenied");
				mav.setViewName("redirect:/user/loginForm.go");
				
			}
			
		}
		
		logger.info("UserController userLogin End");
		
		return mav;
	}

	//---------------------------------------------------------------------------------
	// 회원 로그아웃
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/userLogout.do")
	public ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController userLogout.do Start");
		
		HttpSession session = request.getSession();
		// 세션 값 제거
		System.out.println(session.getAttribute("userInfoId"));
		System.out.println(session.getAttribute("loginResult"));
		
		session.removeAttribute("userInfoId");
		session.removeAttribute("loginResult");
		
		System.out.println(session.getAttribute("userInfoId"));
		System.out.println(session.getAttribute("loginResult"));
		
		ModelAndView mav = new ModelAndView();
		// redirect는 다른 서블릿 맵핑을 요청할 때 여기에서 받은 리퀘스트를 넘겨주지않는다.
		mav.setViewName("redirect:/common/main.go");
		
		logger.info("UserController userLogout.do End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 모든 세션값 지우기
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/removeAllSession.do")
	public ModelAndView removeAllSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController removeAllSession.do Start");
		
		HttpSession session = request.getSession();
		
		// 모든 세션 값 제거
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/common/main.go");
		
		logger.info("UserController removeAllSession.do End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 정보 수정
	//---------------------------------------------------------------------------------
	@RequestMapping(value="/updateUser.do")
	public ModelAndView updateUser(@ModelAttribute("user") UserInfoVO user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("UserController updateUser.do Start");
		
		System.out.println(user);
		
		int result = userService.updateUserInfo(user);
		System.out.println(result);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/myPageInfo.go");
		
		logger.info("UserController updateUser.do End");
		
		return mav;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 정보 삭제
	//---------------------------------------------------------------------------------
	 @RequestMapping(value="/deleteUser.do") 
	 public ModelAndView deleteUser(@ModelAttribute UserInfoVO user, HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		 
		 logger.info("UserController deleteUser.do Start");
		 
		 
		 int result = userService.deleteUserInfo(user.getId());
		 
		 ModelAndView mav = new ModelAndView();
	  
		 mav.setViewName("/common/main.go");
	 
	 	logger.info("UserController deleteUser.do End");
	 	return mav;
	 }
	 
	//-------------------- .do 컨트롤러 - END -----------------------
	 
		 
//------------------------------------- 페이지 기능 맵핑 .do Page - END --------------------------------------------------
		 
}
