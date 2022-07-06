package com.project.billyus.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.PagingVO;
import com.project.billyus.vo.ReserveVO;





@Repository(value="transactionDAO")
public class TransactionDAO {
private static final Logger logger = LoggerFactory.getLogger(TransactionDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	@Autowired
	ReserveVO reserveVO;
	
	
	
	//---------------------------------------------------------------------------------
	// 상품 예약하기 등록
	//---------------------------------------------------------------------------------
	public	int insertReservation(ReserveVO reserveVO) throws DataAccessException {
		
		logger.info("TransactionDAO insertReservation Start");
		
		int result = 0;
		result = sqlSession.insert("mapper.transaction.insertReservation", reserveVO);
		
		logger.info("TransactionDAO insertReservation End");
		
		return result;
	}
//-------------------------------------------------------------
// 판매자 ajax
//-------------------------------------------------------------
	
	
	//---------------------------------------------------------------------------------
	// (판매자)정보로 등록된 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectSearchGoodsList(String user_ID) throws DataAccessException {
	
		List<GoodsByIdVO> goodsList = sqlSession.selectList("mapper.transaction.selectSearchAllGoodsById", user_ID);
		System.out.println(goodsList);
		
		return goodsList;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자)예약 승인중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectSearchReserveList(String user_ID) throws DataAccessException {
	
		List<GoodsByIdVO> reserveList = sqlSession.selectList("mapper.transaction.selectAlltransactionL", user_ID);
		System.out.println(reserveList);
		
		return reserveList;
	}
	//---------------------------------------------------------------------------------
	// (판매자)빌려주기 진행중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectProceedLendingList(String user_ID) throws DataAccessException {
	
		List<GoodsByIdVO> reserveList = sqlSession.selectList("mapper.transaction.lenderProceededTransactionList", user_ID);
		System.out.println(reserveList);
		
		return reserveList;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자)빌려주기 종료된 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectEndLendingList(String user_ID) throws DataAccessException {
	
		List<GoodsByIdVO> reserveList = sqlSession.selectList("mapper.transaction.lenderEndTransactionList", user_ID);
		System.out.println(reserveList);
		
		return reserveList;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자)예약 승인버튼 (update status)
	//---------------------------------------------------------------------------------
	public int approveReserve(int reserve_NUM) throws DataAccessException {
		logger.info("transactionDAO approveReserve Start");
		
		int result = 0;
		result = sqlSession.update("mapper.transaction.approveReserve", reserve_NUM);
		System.out.println(result);
		
		logger.info("transactionDAO approveReserve END");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자)물건 반납받기 확인 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int returned(int reserve_NUM) throws DataAccessException {
		logger.info("transactionDAO approveReserve Start");
		
		int result = 0;
		result = sqlSession.update("mapper.transaction.returned", reserve_NUM);
		System.out.println(result);
		
		logger.info("transactionDAO approveReserve END");
		
		return result;
	}
	//---------------------------------------------------------------------------------
	// (판매자)예약 취소 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int cancelReserve(int reserve_NUM) throws DataAccessException {
		logger.info("transactionDAO approveReserve Start");
		
		int result = 0;
		result = sqlSession.update("mapper.transaction.cancelReserve", reserve_NUM);
		System.out.println(result);
		
		logger.info("transactionDAO approveReserve END");
		
		return result;
	}
//-------------------------------------------------------------
// 구매자 ajax
//-------------------------------------------------------------
	//---------------------------------------------------------------------------------
	// (구매자)예약 승인중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectSearchReserveBorrowList(String user_ID) throws DataAccessException {
	
		List<GoodsByIdVO> reserveList = sqlSession.selectList("mapper.transaction.borrowerTransactionList", user_ID);
		System.out.println(reserveList);
		
		return reserveList;
	}
	
	//---------------------------------------------------------------------------------
	// (구매자)빌리기 진행중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectProceedBorrowList(String user_ID) throws DataAccessException {
	
		List<GoodsByIdVO> reserveList = sqlSession.selectList("mapper.transaction.borrowerProceededTransactionList", user_ID);
		System.out.println(reserveList);
		
		return reserveList;
	}
	//---------------------------------------------------------------------------------
	// (구매자)빌리기 종료된 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectEndBorrowList(String user_ID) throws DataAccessException {
	
		List<GoodsByIdVO> reserveList = sqlSession.selectList("mapper.transaction.borrowerEndTransactionList", user_ID);
		System.out.println(reserveList);
		
		return reserveList;
	}
	//---------------------------------------------------------------------------------
	// (구매자)입금하기 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int deposit(int reserve_NUM) throws DataAccessException {
		logger.info("transactionDAO approveReserve Start");
		
		int result = 0;
		result = sqlSession.update("mapper.transaction.deposit", reserve_NUM);
		System.out.println(result);
		
		logger.info("transactionDAO approveReserve END");
		
		return result;
	}
	//---------------------------------------------------------------------------------
	// (구매자)물품 인수확인 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int takeOver(int reserve_NUM) throws DataAccessException {
		logger.info("transactionDAO approveReserve Start");
		
		int result = 0;
		result = sqlSession.update("mapper.transaction.takeOver", reserve_NUM);
		System.out.println(result);
		
		logger.info("transactionDAO approveReserve END");
		
		return result;
	}
	
//-------------------------------------------------
//  마이페이지 빌리기 내역, 빌려주기 내역 count
//-------------------------------------------------
	//-------------------------------------------------
	//  등록된 상품COUNT
	//-------------------------------------------------
	public int getRegistCount(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = sqlSession.selectOne("mapper.transaction.getRegistCount", user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}
	//-------------------------------------------------
	//  예약 요청중인 상품COUNT
	//-------------------------------------------------
	public int getRequestCount(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = sqlSession.selectOne("mapper.transaction.getRequestCount", user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	//  거래 진행중인 상품COUNT
	//-------------------------------------------------
	public int getProceedCount(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = sqlSession.selectOne("mapper.transaction.getProceedCount", user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	//  종료된 상품COUNT
	//-------------------------------------------------
	public int getEndedCount(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = sqlSession.selectOne("mapper.transaction.getEndedCount", user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}
	
	//-------------------------------------------------
	// (빌리기 내역) 예약 요청중인 상품COUNT 
	//-------------------------------------------------
	public int getRequestCountB(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = sqlSession.selectOne("mapper.transaction.getRequestCountB", user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	// (빌리기 내역) 거래 진행중인 상품COUNT
	//-------------------------------------------------
	public int getProceedCountB(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = sqlSession.selectOne("mapper.transaction.getProceedCountB", user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	// (빌리기 내역) 종료된 상품COUNT
	//-------------------------------------------------
	public int getEndedCountB(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = sqlSession.selectOne("mapper.transaction.getEndedCountB", user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}
	
	//-------------------------------------------------------------
	// example 
	//-------------------------------------------------------------
	public List<GoodsByIdVO> selectLender(String user_ID) throws DataAccessException {
		
		List<GoodsByIdVO> lenderList = sqlSession.selectList("mapper.transaction.lenderTransactionList", user_ID);
		System.out.println(lenderList);
		
		return lenderList;
	}
	
	public List<GoodsByIdVO> selectBorrower(String user_ID) throws DataAccessException {
		
		List<GoodsByIdVO> borrowerList = sqlSession.selectList("mapper.transaction.borrowerTransactionList", user_ID);
		System.out.println(borrowerList);
		
		return borrowerList;
	}
	
	public List<GoodsByIdVO> selectAlltransactionL(String user_ID) throws DataAccessException {
		
		List<GoodsByIdVO> lenderList = sqlSession.selectList("mapper.transaction.selectAlltransactionL", user_ID);
		System.out.println(lenderList);
		
		return lenderList;
	}
	//-------------------------------------------------------------
	// example - End
	//-------------------------------------------------------------
	
	
}
