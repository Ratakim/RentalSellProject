package com.project.billyus.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.project.billyus.vo.ManagerVO;

@Repository
public class ManagerDAO {

	private static final Logger logger = LoggerFactory.getLogger(ManagerDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	
	//--------------------------------------
	//회원가입 실행
	public int insertManager(ManagerVO manager)throws DataAccessException {
		
		int result=0;
		
		result=sqlSession.insert("mapper.manager.insertManager", manager);
		
		return result;
		
	}
	
	//------------------------------------------------
	// 매니저 회원가입 보기
	public List<ManagerVO> selectMember() throws DataAccessException{
		
		List<ManagerVO> memberListd = null;
		
		memberListd = sqlSession.selectList("mapper.manager.selectMember");
		
		return memberListd;
	}
	
}
