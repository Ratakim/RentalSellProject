package com.project.billyus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.billyus.dao.ManagerDAO;
import com.project.billyus.vo.ManagerVO;

@Service(value="managerService")
public class ManagerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerService.class);
	
	@Autowired
	ManagerDAO managerDAO;
	
	
	//----------------------------------------------------
	//회원가입
	public int insertManager(ManagerVO manager)throws DataAccessException {
		
		int result = managerDAO.insertManager(manager);
		
		return result;
		
	}
	
	//--------------------------------------
	// 매니저 리스트
	public List<ManagerVO> selectMember()throws DataAccessException{
		
		List<ManagerVO> memberLists = null;
		memberLists=managerDAO.selectMember();
		
		return memberLists;
	}
	
}
