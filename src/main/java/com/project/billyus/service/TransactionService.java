package com.project.billyus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.billyus.dao.TransactionDAO;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.PagingVO;
import com.project.billyus.vo.ReserveVO;






//---------------------------------------------------------------------------------
//예약 및 거래 transactionService
//---------------------------------------------------------------------------------
@Service(value="transactionService")
public class TransactionService {
private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	TransactionDAO	transactionDAO;
	
	//---------------------------------------------------------------------------------
	// 상품 예약하기 등록
	//---------------------------------------------------------------------------------
	public	int insertReservation(ReserveVO	reserveVO) throws DataAccessException {
		
		logger.info("TransactionService insertReservation Start");
		
		int result = 0;
		result = transactionDAO.insertReservation(reserveVO);
		
		logger.info("TransactionService insertReservation End");
		
		return result;
	}
//-------------------------------------------------------------
// 판매자 ajax
//-------------------------------------------------------------
	
	//---------------------------------------------------------------------------------
	//  (판매자)정보로 등록된 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectSearchGoodsList(String user_ID) throws DataAccessException{
		
		List<GoodsByIdVO> goodsList = transactionDAO.selectSearchGoodsList(user_ID);
		
		return goodsList;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자) 예약 승인중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectSearchReserveList(String user_ID) throws DataAccessException{
		
		List<GoodsByIdVO> reserveList = transactionDAO.selectSearchReserveList(user_ID);
		
		return reserveList;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자) 빌려주기 진행중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectProceedLendingList(String user_ID) throws DataAccessException{
		
		List<GoodsByIdVO> reserveList = transactionDAO.selectProceedLendingList(user_ID);
		
		return reserveList;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자) 빌려주기 종료된 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectEndLendingList(String user_ID) throws DataAccessException{
		
		List<GoodsByIdVO> reserveList = transactionDAO.selectEndLendingList(user_ID);
		
		return reserveList;
	}
	
	//---------------------------------------------------------------------------------
	// (판매자)예약 승인버튼 (update status)
	//---------------------------------------------------------------------------------
	public int approveReserve(int reserve_NUM) throws DataAccessException {
		logger.info("transactionService approveReserve Start");
		
		int result = 0;
		
		result = transactionDAO.approveReserve(reserve_NUM);
		
		logger.info("transactionService approveReserve END");
		
		return result;
	}
	//---------------------------------------------------------------------------------
	// (판매자)물건 반납 받기 확인 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int returned(int reserve_NUM) throws DataAccessException {
		logger.info("transactionService approveReserve Start");
		
		int result = 0;
		
		result = transactionDAO.returned(reserve_NUM);
		
		logger.info("transactionService approveReserve END");
		
		return result;
	}
	//---------------------------------------------------------------------------------
	// (판매자)예약 취소 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int cancelReserve(int reserve_NUM) throws DataAccessException {
		logger.info("transactionService approveReserve Start");
		
		int result = 0;
		
		result = transactionDAO.cancelReserve(reserve_NUM);
		
		logger.info("transactionService approveReserve END");
		
		return result;
	}
//-------------------------------------------------------------
// 구매자 ajax
//-------------------------------------------------------------
	//---------------------------------------------------------------------------------
	//(구매자) 예약 승인중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectSearchReserveBorrowList(String user_ID) throws DataAccessException{
		
		List<GoodsByIdVO> reserveList = transactionDAO.selectSearchReserveBorrowList(user_ID);
		
		return reserveList;
	}
	//---------------------------------------------------------------------------------
	//(구매자) 빌리기 진행중인 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectProceedBorrowList(String user_ID) throws DataAccessException{
		
		List<GoodsByIdVO> reserveList = transactionDAO.selectProceedBorrowList(user_ID);
		
		return reserveList;
	}
	//---------------------------------------------------------------------------------
	//(구매자) 빌리기 종료된 상품, 썸네일 이미지 가져오기
	//---------------------------------------------------------------------------------
	public List<GoodsByIdVO> selectEndBorrowList(String user_ID) throws DataAccessException{
		
		List<GoodsByIdVO> reserveList = transactionDAO.selectEndBorrowList(user_ID);
		
		return reserveList;
	}
	//---------------------------------------------------------------------------------
	// (구매자) 입금하기 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int deposit(int reserve_NUM) throws DataAccessException {
		logger.info("transactionService approveReserve Start");
		
		int result = 0;
		
		result = transactionDAO.deposit(reserve_NUM);
		
		logger.info("transactionService approveReserve END");
		
		return result;
	}
	
	//---------------------------------------------------------------------------------
	// (구매자) 물품 인수확인 버튼 (update status)
	//---------------------------------------------------------------------------------
	public int takeOver(int reserve_NUM) throws DataAccessException {
		logger.info("transactionService approveReserve Start");
		
		int result = 0;
		
		result = transactionDAO.takeOver(reserve_NUM);
		
		logger.info("transactionService approveReserve END");
		
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
		
		int getCount = transactionDAO.getRegistCount(user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}
	//-------------------------------------------------
	//  예약 요청중인 상품COUNT
	//-------------------------------------------------
	public int getRequestCount(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = transactionDAO.getRequestCount(user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	//  거래 진행중인 상품COUNT
	//-------------------------------------------------
	public int getProceedCount(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = transactionDAO.getProceedCount(user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	//  종료된 상품COUNT
	//-------------------------------------------------
	public int getEndedCount(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = transactionDAO.getEndedCount(user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}
	
	//-------------------------------------------------
	//  예약 요청중인 상품COUNT
	//-------------------------------------------------
	public int getRequestCountB(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = transactionDAO.getRequestCountB(user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	//  거래 진행중인 상품COUNT
	//-------------------------------------------------
	public int getProceedCountB(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = transactionDAO.getProceedCountB(user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	//-------------------------------------------------
	//  종료된 상품COUNT
	//-------------------------------------------------
	public int getEndedCountB(String user_ID) throws DataAccessException {
		
		logger.info("UserService getRegistCount Start");
		
		int getCount = transactionDAO.getEndedCountB(user_ID);
		
		logger.info("UserService getRegistCount End");
		
		return getCount;
	}

	
	
	
	//-------------------------------------------------------------
	// example 
	//-------------------------------------------------------------
	public List<GoodsByIdVO> selectLender(String user_ID) throws DataAccessException {
		
		List<GoodsByIdVO> lenderList = transactionDAO.selectLender(user_ID);
		System.out.println(lenderList);
		
		return lenderList;
	}

	public List<GoodsByIdVO> selectBorrower(String user_ID) throws DataAccessException {
		
		List<GoodsByIdVO> borrowerList = transactionDAO.selectBorrower(user_ID);
		System.out.println(borrowerList);
		
		return borrowerList;
	}
	
	public List<GoodsByIdVO> selectAlltransactionL(String user_ID) throws DataAccessException {
		
		List<GoodsByIdVO> lenderList = transactionDAO.selectAlltransactionL(user_ID);
		System.out.println(lenderList);
		
		return lenderList;
	}
	
	//-------------------------------------------------------------
	// example - End
	//-------------------------------------------------------------
}
