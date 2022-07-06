package com.project.billyus.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.billyus.dao.BoardDAO;
import com.project.billyus.dao.CommonDAO;
import com.project.billyus.dao.GoodsDAO;
import com.project.billyus.dao.UserDAO;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.GoodsImgFileVO;
import com.project.billyus.vo.RegistCtgVO;

//---------------------------------------------------------------------------------
// 메인 서비스 CommonService
//---------------------------------------------------------------------------------
@Service(value="commonService")
public class CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);
	
	@Autowired
	GoodsDAO 	goodsDAO;
	@Autowired
	UserDAO 	userDAO;
	@Autowired
	CommonDAO 	commonDAO;
	@Autowired
	BoardDAO 	boardDAO;
	@Autowired
	RegistCtgVO registCtgVO;
		
	//---------------------- ajax ---------------------------
	
	//---------------------------------------------------------------------------------
	// 검색어 가져오기 ajax
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> searchList(String name) throws DataAccessException {
		
		logger.info("CommonService searchList Start");
		
		List<RegistCtgVO> listCategory = null; 
		
		listCategory = commonDAO.selectSearchNameList(name);
		
		logger.info("CommonService searchList END");
		
		return listCategory;
		
	}
	
	//-------------------- ajax - END -----------------------
	
	//---------------------- 일반 서비스 ---------------------------
	
	
	
	//-------------------- 일반 서비스 - END -----------------------
}
