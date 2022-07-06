package com.project.billyus.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.billyus.dao.UserDAO;
import com.project.billyus.vo.BoardVO;
import com.project.billyus.vo.UserCertifyVO;
import com.project.billyus.vo.UserInfoVO;

//---------------------------------------------------------------------------------
// 유저 서비스 UserService
//---------------------------------------------------------------------------------
@Service(value="userService")
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDAO userDAO;
	
	//---------------------- ajax ---------------------------
	public int idCheck(String id) throws DataAccessException {

		int result = userDAO.idCheck(id);

		return result;
	}
	
	//-------------------- ajax - END -----------------------

	//---------------------- 일반 서비스 ---------------------------
	
	//---------------------------------------------------------------------------------
	// 회원가입 실행
	//---------------------------------------------------------------------------------
	public int insertUser(UserInfoVO user) throws DataAccessException {
		
		logger.info("UserService insertUser Start");
		
		int result = 0;
		result = userDAO.insertUser(user);
		
		logger.info("UserService insertUser END");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// id로 회원 정보 보기
	//---------------------------------------------------------------------------------
	public UserInfoVO userInfo(String userId) throws DataAccessException {
		
		logger.info("UserService userInfo Start");
		
		UserInfoVO customerVO = null; 
		
		customerVO = userDAO.userInfo(userId);
		
		logger.info("UserService userInfo END");
		
		return customerVO;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 정보 수정
	//---------------------------------------------------------------------------------
	public int updateUserInfo(UserInfoVO user) throws DataAccessException {
		
		logger.info("UserService updateCustomer Start");
		
		int result = 0;
		
		result = userDAO.updateUserInfo(user);
		
		logger.info("UserService updateCustomer END");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 로그인 성공 시 마지막 접속일 업데이트
	//---------------------------------------------------------------------------------
	public int updateUserLastJoin(String userId) throws DataAccessException {
		
		logger.info("UserService updateCustomerLastJoin Start");
		
		int result = 0;
		
		result = userDAO.updateUserLastJoin(userId);
		
		logger.info("UserService updateCustomerLastJoin End");
		
		return 	result;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 정보 삭제
	//---------------------------------------------------------------------------------
	public int deleteUserInfo(String userId) throws DataAccessException {
		
		logger.info("UserService deleteCutomerInfo Start");
		
		int result = 0;
		
		result = userDAO.deleteUserInfo(userId);
		
		logger.info("UserService deleteCutomerInfo End");
		
		return result;
	}
	
	// ---------------------------------------------------------------------------------
	// 계좌 등록
	// ---------------------------------------------------------------------------------
	public void insertUserAccount(UserCertifyVO vo) throws Exception {

		userDAO.insertUserAccount(vo);
	}
	
	// ---------------------------------------------------------------------------------
	// 카드 등록
	// ---------------------------------------------------------------------------------
	public void insertUserCard(UserCertifyVO vo) throws Exception {

		userDAO.insertUserCard(vo);
	}
	
	// -------------------------------------------------
	// 계좌 불러오기
	// -------------------------------------------------
	public List<UserCertifyVO> selectAccount() throws DataAccessException {

		logger.info("UserService selectAccount Start");

		List<UserCertifyVO> selectAccount = null;
		selectAccount = userDAO.selectAccount();

		logger.info("UserService selectAccount End");

		return selectAccount;
	}
	
	// -------------------------------------------------
	// 카드 불러오기
	// -------------------------------------------------
	public List<UserCertifyVO> selectCard() throws DataAccessException {

		logger.info("UserService selectCard Start");

		List<UserCertifyVO> selectCard = null;
		selectCard = userDAO.selectCard();

		logger.info("UserService selectCard End");

		return selectCard;
	}
	
	// ---------------------------------------------------------------------------------
	// 해당 아이디에 계좌가 등록되어 있는지
	// ---------------------------------------------------------------------------------
	public int selectCountAccount(String id) throws DataAccessException {

		logger.info("UserService selectCountAccount Start");
		
		int result = userDAO.selectCountAccount(id);

		logger.info("UserService selectCountAccount End");

		return result;
	}
	
	// ---------------------------------------------------------------------------------
	// 해당 아이디에 카드가 등록되어 있는지
	// ---------------------------------------------------------------------------------
	public int selectCountCard(String id) throws DataAccessException {

		logger.info("UserService selectCountCard Start");
		
		int result = userDAO.selectCountCard(id);

		logger.info("UserService selectCountCard End");

		return result;
	
	}
	
	// ---------------------------------------------------------------------------------
	// 이름과 유저 이메일로 정보가 존재하는지 찾기
	// ---------------------------------------------------------------------------------
	public UserInfoVO findUserInfo(UserInfoVO userInfoVO) throws DataAccessException {
		
		logger.info("UserService findUserInfo Start");
		
		UserInfoVO userExists = userDAO.findUserInfo(userInfoVO);
		
		logger.info("UserService findUserInfo End");
		
		return userExists;
	}
	
	// ---------------------------------------------------------------------------------
	// 이름과 유저 이메일로 정보가 존재하는지 찾기
	// ---------------------------------------------------------------------------------
	public int updateUserPwd(Map<String, String> map) throws DataAccessException {
		
		logger.info("UserService updateUserPwd Start");
		
		int result = userDAO.updateUserPwd(map);
		
		logger.info("UserService updateUserPwd End");
		
		return result;
	}
	
		
	//-------------------- 일반 서비스 - END -----------------------

}
