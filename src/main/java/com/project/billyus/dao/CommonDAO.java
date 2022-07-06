package com.project.billyus.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.GoodsImgFileVO;
import com.project.billyus.vo.RegistCtgVO;

//---------------------------------------------------------
// 메인 DAO CommonDAO
//---------------------------------------------------------
@Repository(value="commonDAO")
public class CommonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	@Autowired
	RegistCtgVO registCtgVO;
	
	//---------------------------------------------------------------------------------
	// 검색어 가져오기 ajax
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> selectSearchNameList(String name) throws DataAccessException {
		
		logger.info("CommonDAO selectSearchNameList Start");
		
		System.out.println(name);
		
		List<RegistCtgVO> searchList = null;
		
		int count = 0;
		count = sqlSession.selectOne("mapper.common.selectCountRow", name);
		
		System.out.println(count);
		if( count == 0 ) {
			searchList = new ArrayList<RegistCtgVO>();
			registCtgVO.setCategory_ID(count);
			registCtgVO.setCategory_NM("검색 실패 :" + name);
			searchList.add(registCtgVO);
		} else {
			searchList = sqlSession.selectList("mapper.common.selectSearchList", name);
		}
		
		System.out.println(searchList);
		
		logger.info("CommonDAO selectSearchNameList End");
		
		return searchList;
	}
	

	

	
}
