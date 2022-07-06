package com.project.billyus.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.billyus.dao.GoodsDAO;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.GoodsImgFileVO;
import com.project.billyus.vo.PagingVO;
import com.project.billyus.vo.RegistCtgVO;

//---------------------------------------------------------------------------------
// 상품 컨트롤러 ProductService
//---------------------------------------------------------------------------------
@Service(value="goodsService")
public class GoodsService {

	private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);
	
	@Autowired
	GoodsDAO goodsDAO;
	
	//---------------------- ajax ---------------------------
	
	//---------------------------------------------------------------------------------
	// 대분류 카테고리 가져오기
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> listCtg() throws DataAccessException {
		
		logger.info("GoodsService listCtg Start");
		
		List<RegistCtgVO> listCategory = null;
		listCategory = goodsDAO.selectRgCtgNameList();
		
		logger.info("GoodsService listCtg END");
		
		return listCategory;
	}
	
	//---------------------------------------------------------------------------------
	// 중분류 카테고리 가져오기 ajax
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> getCategoryM(String category_NM) throws DataAccessException {
		
		logger.info("GoodsService getCategoryM Start");
		
		List<RegistCtgVO> listCategory = null;
		listCategory = goodsDAO.selectCategoryM(category_NM);
		
		logger.info("GoodsService getCategoryM END");
		
		return listCategory;
	}
	
	//---------------------------------------------------------------------------------
	// 소분류 카테고리 가져오기 ajax
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> getCategoryS(String category_NM ) throws DataAccessException {
		
		logger.info("GoodsService searchList Start");
		
		List<RegistCtgVO> listCategory = null; 
		listCategory = goodsDAO.selectCategoryS(category_NM);
		
		logger.info("GoodsService searchList END");
		
		return listCategory;
	}
	
	//-------------------- ajax - END -----------------------
	
	//---------------------- 일반 서비스 ---------------------------
	
	//---------------------------------------------------------------------------------
	// 제품 누르면 제품 상세 페이지 
	//---------------------------------------------------------------------------------
	public GoodsByIdVO goodsContent(int goods_code) throws DataAccessException {
		
		logger.info("GoodsService goodsContent Start");
		
		GoodsByIdVO ContentList = null; 
		ContentList = goodsDAO.goodsContent(goods_code);
		
		logger.info("GoodsService goodsContent END");
		
		return ContentList;
	}

	//---------------------------------------------------------------------------------
	// 상품페이지에서 카테고리이름만 불러오기
	//---------------------------------------------------------------------------------
	public RegistCtgVO getGoodsCtgId(int goods_code) throws DataAccessException {
		
		logger.info("GoodsService getGoodsCtgId Start");
		
		RegistCtgVO GetCategoryNM = null; 
		GetCategoryNM = goodsDAO.getGoodsCategory_NM(goods_code);
		
		logger.info("GoodsService getGoodsCtgId END");
		
		return GetCategoryNM;
	}
	
	//---------------------------------------------
	// 상품 상세페이지 조회시 조회수 증가 Service
	//---------------------------------------------
	public int goodsViewCount(int goods_code) throws DataAccessException {
		
		int goods_hit = goodsDAO.goodsViewCount(goods_code);
		System.out.println("증가된 조회수 :"+ goods_hit);
		
		return goods_hit;
		
	}
	
	//---------------------------------------------------------------------------------
	// 제품 리스트 게시판 페이지
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> getGoodsList(PagingVO vo) throws DataAccessException {
		
		logger.info("GoodsService getGoodsList Start");
		
		List<GoodsByIdVO> goodsList = null;
		goodsList = goodsDAO.selectGoodsList(vo);
		
		
		logger.info("GoodsService getGoodsList End");
		return goodsList;
	}
	
	//---------------------------------------------------------------------------------
	// 검색한 제품 리스트 게시판 페이지 전체 데이터 개수 가져오기
	//---------------------------------------------------------------------------------
	public int getTotalRow(PagingVO vo) throws DataAccessException {
		
		logger.info("GoodsService getTotalRow Start");
		
		int getRow = goodsDAO.getTotalRow(vo);
		System.out.println("getRow = " + getRow);
		logger.info("GoodsService getTotalRow End");
		
		return getRow;
	}
	
	//---------------------------------------------------------------------------------
	// 모든 제품 리스트 게시판 페이지 전체 데이터 개수 가져오기
	//---------------------------------------------------------------------------------
	public int getAllTotalRow() throws DataAccessException {
		
		logger.info("GoodsService getTotalRow Start");
		
		int getRow = goodsDAO.getAllTotalRow();
		System.out.println("getRow = " + getRow);
		logger.info("GoodsService getTotalRow End");
		
		return getRow;
	}
	
	//---------------------------------------------------------------------------------
	// 상품 게시판 등록하기
	//---------------------------------------------------------------------------------
	public	int insertGoodsBoard(GoodsByIdVO goodsByIdVO) throws DataAccessException {
		
		logger.info("GoodsService insertGoodsBoard Start");
		
		int result = 0;
		result = goodsDAO.insertGoodsBoard(goodsByIdVO);
		
		logger.info("GoodsService insertGoodsBoard End");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// 아이디에 맞는 최근 goods_code 불러오기
	//---------------------------------------------------------------------------------
	public	int selectRecentGoodsCode(String user_ID) throws DataAccessException {
		
		logger.info("GoodsService selectRecentGoodsCode Start");
		
		int goods_code = goodsDAO.selectRecentGoodsCode(user_ID);
		System.out.println("selectRecentGoodsCode = " + goods_code);
		
		logger.info("GoodsService selectRecentGoodsCode End");
		
		return goods_code;
	}
	
	//---------------------------------------------------------------------------------
	// 상품 게시판 이미지 파일 리스트 등록하기
	//---------------------------------------------------------------------------------
	public	int insertGoodsImgFile(List<GoodsImgFileVO> goodsImgFileList) throws DataAccessException {
		
		logger.info("GoodsService insertGoodsImgFile Start");
		
		int result = 0;
		result = goodsDAO.insertGoodsImgFile(goodsImgFileList);
		
		logger.info("GoodsService insertGoodsImgFile End");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// 해당 상품 게시판, 이미지 리스트 불러오기
	//-----------------------------------------------------------------------------
	public GoodsByIdVO selectGoodsByIdBoard(int goods_code) throws DataAccessException{
		
		GoodsByIdVO goodsByIdVO = goodsDAO.selectGoodsByIdBoard(goods_code);
		
		return goodsByIdVO;
	}
	
	//---------------------------------------------------------------------------------
	// 모든 상품 게시판 VO, 썸네일 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectGoodsBoaedList(PagingVO vo) throws DataAccessException{
		
		List<GoodsByIdVO> goodsList = goodsDAO.selectGoodsBoaedList(vo);
		
		return goodsList;
	}
	
	
	//-------------------- 일반 서비스 - END -----------------------
	
}
