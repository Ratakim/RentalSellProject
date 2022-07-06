package com.project.billyus.dao;

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
import com.project.billyus.vo.PagingVO;
import com.project.billyus.vo.RegistCtgVO;

//---------------------------------------------------------
// 상품 DAO ProductDAO
//---------------------------------------------------------
@Repository(value="productDAO")
public class GoodsDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	@Autowired
	RegistCtgVO registCtgVO;
	
	
//--------------------------- 카테고리 -----------------------------------------------
	
	//---------------------------------------------------------------------------------
	// 대분류 카테고리 가져오기
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> selectRgCtgNameList() throws DataAccessException {
			
		logger.info("GoodsDAO selectRgCtgNameList Start");
		
		List<RegistCtgVO> ctgList = null;
		ctgList = sqlSession.selectList("mapper.goods.selectRgCtgNameList");
		System.out.println(ctgList);
		
		logger.info("GoodsDAO selectRgCtgNameList End");
		
		return ctgList;
	}
	
	//---------------------------------------------------------------------------------
	// 중분류 카테고리 가져오기 ajax
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> selectCategoryM(String category_NM) throws DataAccessException {

		logger.info("GoodsDAO selectCategoryM Start");
		System.out.println(category_NM);
		
		List<RegistCtgVO> ctgList = null;		
		ctgList = sqlSession.selectList("mapper.goods.selectCategoryM", category_NM);
		System.out.println(ctgList);
		
		logger.info("GoodsDAO selectCategoryM End");
		
		return ctgList;
	}
	
	//---------------------------------------------------------------------------------
	// 소분류 카테고리 가져오기 ajax
	//---------------------------------------------------------------------------------
	public List<RegistCtgVO> selectCategoryS(String category_NM) throws DataAccessException {
		logger.info("GoodsDAO selectCategoryS Start");
		System.out.println(category_NM);
		
		List<RegistCtgVO> ctgList = null;		
		ctgList = sqlSession.selectList("mapper.goods.selectCategoryS", category_NM );
		System.out.println(ctgList);
		
		logger.info("GoodsDAO selectCategoryS End");
		
		return ctgList;
	}
	
	//---------------------------------------------------------------------------------
	// 상품페이지에서 카테고리이름만 불러오기
	//---------------------------------------------------------------------------------
	public RegistCtgVO getGoodsCategory_NM(int goods_code) throws DataAccessException {
		
		logger.info("GoodsDAO goodsContent Start");
		System.out.println(goods_code);
		
		RegistCtgVO category_NM =  null;
		category_NM = sqlSession.selectOne("mapper.goods.selectGoodsCategoryNM", goods_code);
		
		System.out.println(category_NM);
		
		
		logger.info("GoodsDAO goodsContent End");
		
		return category_NM;
	}
	
//--------------------------- 카테고리 -----------------------------------------------
	
//--------------------------- 상품 게시판 -----------------------------------------------
	
	//---------------------------------------------------------------------------------
	// 제품 누르면 제품 상세 페이지 
	//---------------------------------------------------------------------------------
	public GoodsByIdVO goodsContent(int goods_code) throws DataAccessException {
		
		logger.info("GoodsDAO goodsContent Start");
		System.out.println(goods_code);
		
		GoodsByIdVO goodsByIdVO = null;		
		goodsByIdVO = sqlSession.selectOne("mapper.goods.selectGoods", goods_code);
		System.out.println(goodsByIdVO);
		
		int category_ID = goodsByIdVO.getCategory_ID();
		System.out.println(goodsByIdVO);
		
		logger.info("GoodsDAO goodsContent End");
		
		return goodsByIdVO;
	}

	//---------------------------------------------
	// 상품 상세페이지 조회시 조회수 증가 DAO
	//---------------------------------------------
	public int goodsViewCount(int goods_code) throws DataAccessException {
		
		logger.info("GoodsDAO goodsViewCount End");
		
		int goods_hit = sqlSession.update("mapper.goods.goodsViewCount", goods_code);
		
		logger.info("GoodsDAO goodsViewCount End");
		
		return goods_hit;
		
		
	}
	
	//---------------------------------------------------------------------------------
	// 제품 리스트 게시판 페이지) 리스트 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectGoodsList(PagingVO vo) throws DataAccessException {
		
		logger.info("GoodsDAO selectGoodsList Start");
		
		List<GoodsByIdVO> goodsList = null;
		goodsList = sqlSession.selectList("mapper.goods.selectAllGoods", vo);
		
		System.out.println(goodsList);
		
		logger.info("GoodsDAO selectGoodsList End");
		return goodsList;
	}
	
	//---------------------------------------------------------------------------------
	// 검색한 제품 리스트 게시판 페이지) 전체 데이터 개수 가져오기
	//---------------------------------------------------------------------------------
	public int getTotalRow(PagingVO vo) throws DataAccessException {
		logger.info("GoodsDAO getTotalRow Start");
		
		int getRow = sqlSession.selectOne("mapper.goods.getTotalRow", vo);
		System.out.println("getRow = " + getRow);
		logger.info("GoodsDAO getTotalRow End");
		return getRow;
	}
	
	//---------------------------------------------------------------------------------
	// 모든 제품 리스트 게시판 페이지 전체 데이터 개수 가져오기
	//---------------------------------------------------------------------------------
	public int getAllTotalRow() throws DataAccessException {
		
		logger.info("GoodsService getTotalRow Start");
		
		int getRow = sqlSession.selectOne("mapper.goods.getAllTotalRow");
		System.out.println("getRow = " + getRow);
		logger.info("GoodsService getTotalRow End");
		
		return getRow;
	}
	
	
	//---------------------------------------------------------------------------------
	// 상품 게시판 등록하기
	//---------------------------------------------------------------------------------
	public	int insertGoodsBoard(GoodsByIdVO goodsByIdVO) throws DataAccessException {
		
		logger.info("GoodsDAO insertGoodsBoard Start");
		
		int result = 0;
		result = sqlSession.insert("mapper.goods.insertGoodsBoard", goodsByIdVO);
		
		logger.info("GoodsDAO insertGoodsBoard End");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// 아이디에 맞는 최근 등록 게시판 코드 가져오기
	//---------------------------------------------------------------------------------
	public int selectRecentGoodsCode(String user_ID) throws DataAccessException {
	
		logger.info("GoodsDAO selectRecentGoodsCode Start");
		
		int goods_code  = sqlSession.selectOne("mapper.goods.selectRecentGoodsCode", user_ID);
		
		logger.info("GoodsDAO selectRecentGoodsCode End");
		
		return goods_code;
	}
	
	//---------------------------------------------------------------------------------
	// 상품 게시판 이미지 파일 등록하기
	//---------------------------------------------------------------------------------
	public	int insertGoodsImgFile(List<GoodsImgFileVO> goodsImgFileList) throws DataAccessException {
		
		logger.info("GoodsDAO insertGoodsImgFile Start");
		
		int result = 0;
		for (int i = 0; i < goodsImgFileList.size(); i++ ) {
			GoodsImgFileVO goodsImgFileVO = goodsImgFileList.get(i);
			result  += sqlSession.insert("mapper.goods.insertGoodsImgFile", goodsImgFileVO);			
		}
		System.out.println(goodsImgFileList.size() == result? "success" : "fail");
		
		logger.info("GoodsDAO insertGoodsImgFile End");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// 해당 상품 게시판, 이미지 리스트 불러오기
	//---------------------------------------------------------------------------------
	public GoodsByIdVO selectGoodsByIdBoard(int goods_code) throws DataAccessException {
		logger.info("GoodsDAO selectGoodsByIdBoard Start");
		
		GoodsByIdVO goodsByIdVO = sqlSession.selectOne("mapper.goods.selectGoodsBoard", goods_code);
		System.out.println(goodsByIdVO);
		System.out.println(goodsByIdVO.getGoods_code());
		
		logger.info("GoodsDAO selectGoodsByIdBoard End");
		
		return goodsByIdVO;
	}
	
	//---------------------------------------------------------------------------------
	// 모든 상품 게시판 VO, 썸네일 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectGoodsBoaedList(PagingVO pagingVO) throws DataAccessException {
	
		logger.info("GoodsDAO selectGoodsBoaedList Start");
		
		List<GoodsByIdVO> goodsList = sqlSession.selectList("mapper.goods.selectGoodsBoardJoinFilesSearch", pagingVO);
		System.out.println(goodsList);
		
		logger.info("GoodsDAO selectGoodsBoaedList End");
		
		return goodsList;
	}
	
	//--------------------------- 상품 게시판 -----------------------------------------------
	
}
