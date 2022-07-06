package com.project.billyus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.billyus.dao.BoardDAO;
import com.project.billyus.vo.BoardVO;
import com.project.billyus.vo.ReplyVO;

//---------------------------------------------------------------------------------
// 게시판 서비스 BoardService
//---------------------------------------------------------------------------------
@Service(value = "boardService")
public class BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	BoardDAO boardDAO;

	// ---------------------- ajax ---------------------------

	// -------------------- ajax - END -----------------------

	// ---------------------- 일반 서비스 ---------------------------

	// -------------------------------------------------
	// 게시판 카테고리 이동
	// -------------------------------------------------
	public List<BoardVO> selectBoard(String board_Type) throws DataAccessException {

		logger.info("BoardService BoardAll Start");

		List<BoardVO> BoardAll = null;
		BoardAll = boardDAO.selectBoard(board_Type);

		logger.info("BoardService BoardAll End");

		return BoardAll;
	}

	// -------------------------------------------------
	// 게시판 상세보기
	// -------------------------------------------------
	public BoardVO boardDetail(int article_NO) throws DataAccessException {

		logger.info("BoardService BoardAll Start");

		BoardVO boardDetail = null;
		boardDetail = boardDAO.boardDetail(article_NO);

		logger.info("BoardService BoardAll End");

		return boardDetail;
	}

	// -------------------------------------------------
	// 게시판 작성
	// -------------------------------------------------
	public void boardWrite(BoardVO vo) throws DataAccessException {

		logger.info("BoardService BoardAll Start");

		boardDAO.boardWrite(vo);

		logger.info("BoardService BoardAll End");
	}

	// -------------------------------------------------
	// 게시판 작성
	// -------------------------------------------------
	public void boardDelete(int article_NO) throws DataAccessException {

		logger.info("BoardService boardDelete Start");

		boardDAO.boardDelete(article_NO);

		logger.info("BoardService boardDelete End");
	}

	// -------------------------------------------------
	// 게시판 수정
	// -------------------------------------------------
	public void boardModify(BoardVO mo) throws DataAccessException {

		logger.info("BoardService boardModify Start");

		boardDAO.boardModify(mo);

		logger.info("BoardService boardModify End");
	}

	// ---------------------------------------------------------------------------------
	// 게시글 페이징
	// ---------------------------------------------------------------------------------
	public List<BoardVO> boardPaging(String board_Type, int displayPost, int postNum, String searchType, String keyword) throws Exception {

		logger.info("BoardService boardPaging Start");

		List<BoardVO> boardPaging = boardDAO.boardPaging(board_Type,displayPost, postNum ,searchType, keyword);

		logger.info("BoardService boardPaging End");

		return boardPaging;
	}

	// ---------------------------------------------------------------------------------
	// 게시글 총 개수
	// ---------------------------------------------------------------------------------
	public int count(String val, String searchType, String keyword) throws Exception {

		return boardDAO.count(val, searchType, keyword);
	}

	// -------------------- 일반 서비스 - END -----------------------
	
	//이 아래는 댓글 관련 입니다 
	
	// 댓글 조회
	
	public List<ReplyVO> replylist(int bno) throws Exception {
		return boardDAO.replylist(bno);
	}

	// 댓글 작성
	
	public void replywrite(ReplyVO vo) throws Exception {
		boardDAO.replywrite(vo);
	}

	// 댓글 수정

	public void replymodify(ReplyVO vo) throws Exception {
		boardDAO.replymodify(vo);
	}

	// 댓글 삭제
	
	public void replydelete(ReplyVO vo) throws Exception {
		boardDAO.replydelete(vo);
	}

}
