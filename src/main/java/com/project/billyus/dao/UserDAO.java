package com.project.billyus.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.project.billyus.vo.RegistCtgVO;
import com.project.billyus.vo.UserCertifyVO;
import com.project.billyus.vo.UserInfoVO;

//---------------------------------------------------------
// 유저 DAO UserDAO
//---------------------------------------------------------
@Repository(value="userDAO")
public class UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	@Autowired
	RegistCtgVO registCtgVO;

	//---------------------------------------------------------------------------------
	// 회원가입 실행
	//---------------------------------------------------------------------------------
	public int insertUser(UserInfoVO user) throws DataAccessException {
		
		logger.info("UserDAO insertUser Start");
		
		int result = 0;
		result = sqlSession.insert("mapper.user.insertUserInfo", user);
		
		logger.info("UserDAO insertUser End");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// 아이디 유효성검사
	//---------------------------------------------------------------------------------
	public int idCheck(String id) { // 유저가 입력한 값을 매개변수
		
		System.out.println(id);
		
		int result = sqlSession.selectOne("mapper.user.selectCountRow", id);
		
		System.out.println(result);
		/* String rs = Integer.toString(result); */
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// id로 회원 정보 보기
	//---------------------------------------------------------------------------------
	public UserInfoVO userInfo(String userId) throws DataAccessException {
		
		logger.info("UserDAO userInfo Start");
		
		UserInfoVO userInfoVO = null;
		userInfoVO = sqlSession.selectOne("mapper.user.selectUserById", userId);
		
		logger.info("UserDAO userInfo End");
		
		return userInfoVO;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 로그인 성공 시 마지막 접속일 업데이트
	//---------------------------------------------------------------------------------
	public int updateUserLastJoin(String userId) throws DataAccessException {
		
		logger.info("UserDAO updateUserLastJoin Start");
		
		int result = 0; 
		result = sqlSession.update("mapper.user.updateUserLastJoin", userId);
		
		logger.info("UserDAO updateUserLastJoin End");
		
		return 	result;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 정보 수정
	//---------------------------------------------------------------------------------
	public int updateUserInfo(UserInfoVO user) throws DataAccessException {
		
		
		logger.info("UserDAO updateUserInfo Start");
		
		int result = 0;
		result = sqlSession.update("mapper.user.updateUserInfo", user);
		System.out.println(result);
		
		logger.info("UserDAO updateUserInfo End");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// 회원 정보 삭제
	//---------------------------------------------------------------------------------
	public int deleteUserInfo(String userId) throws DataAccessException {

		logger.info("UserDAO deleteUserInfo Start");
		
		int result = 0;
		result = sqlSession.delete("mapper.user.deleteUserInfo", userId);
		
		logger.info("UserDAO deleteUserInfo End");
		
		return result;
	}
	
	// ---------------------------------------------------------------------------------
	// 계좌 등록
	// ---------------------------------------------------------------------------------
	public void insertUserAccount(UserCertifyVO vo) throws DataAccessException {

		logger.info("UserDAO insertUserAccount Start");

		sqlSession.insert("mapper.user.insertUserAccount", vo);

		logger.info("UserDAO insertUserAccount End");
	}

	// ---------------------------------------------------------------------------------
	// 카드 등록
	// ---------------------------------------------------------------------------------
	public void insertUserCard(UserCertifyVO vo) throws DataAccessException {

		logger.info("UserDAO insertUserCard Start");

		sqlSession.insert("mapper.user.insertUserCard", vo);

		logger.info("UserDAO insertUserCard End");
	}
	
	// ---------------------------------------------------------------------------------
	// 계좌 정보 불러오기
	// ---------------------------------------------------------------------------------
	public List<UserCertifyVO> selectAccount() throws DataAccessException {

		logger.info("UserDAO selectAccount Start");

		List<UserCertifyVO> Account = null;
		Account = sqlSession.selectList("mapper.user.UserAccount");

		logger.info("UserDAO selectAccount End");

		return Account;
	}
	
	// ---------------------------------------------------------------------------------
	// 카드 정보 불러오기
	// ---------------------------------------------------------------------------------
	public List<UserCertifyVO> selectCard() throws DataAccessException {

		logger.info("UserDAO selectCard Start");

		List<UserCertifyVO> Card = null;
		Card = sqlSession.selectList("mapper.user.UserCard");

		logger.info("UserDAO selectCard End");

		return Card;
	}
	
	// ---------------------------------------------------------------------------------
	// 해당 아이디에 계좌가 등록되어 있는지
	// ---------------------------------------------------------------------------------
	public int selectCountAccount(String id) throws DataAccessException {

		logger.info("UserDAO selectCard Start");
		
		int result = sqlSession.selectOne("mapper.user.countAccount", id);

		logger.info("UserDAO selectCard End");

		return result;
	}
	
	// ---------------------------------------------------------------------------------
	// 해당 아이디에 카드가 등록되어 있는지
	// ---------------------------------------------------------------------------------
	public int selectCountCard(String id) throws DataAccessException {

		logger.info("UserDAO selectCard Start");
		
		int result = sqlSession.selectOne("mapper.user.countCard", id);

		logger.info("UserDAO selectCard End");

		return result;
	}
	
	// ---------------------------------------------------------------------------------
	// 매개 변수 정보로 회원 정보가 존재하는지 찾기
	// ---------------------------------------------------------------------------------
	public UserInfoVO findUserInfo(UserInfoVO userInfoVO) throws DataAccessException {
		
		logger.info("UserDAO findUserInfo Start");
		
		UserInfoVO userExists = sqlSession.selectOne("mapper.user.findUserInfo", userInfoVO);
		
		logger.info("UserDAO findUserInfo End");
		
		return userExists;
	}
	
	// ---------------------------------------------------------------------------------
	// 이름과 유저 이메일로 정보가 존재하는지 찾기
	// ---------------------------------------------------------------------------------
	public int updateUserPwd(Map<String, String> map) throws DataAccessException {
		
		logger.info("UserDAO updateUserPwd Start");
		
		int result = sqlSession.update("mapper.user.updateUserPwd", map);
		System.out.println("UserDAO updateUserPwd result = " + result);
		
		logger.info("UserDAO updateUserPwd End");
		
		return result;
	}
	
}
