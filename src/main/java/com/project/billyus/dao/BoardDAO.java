package com.project.billyus.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.project.billyus.vo.BoardVO;
import com.project.billyus.vo.ReplyVO;

//---------------------------------------------------------
// 게시판 DAO BoardDAO
//---------------------------------------------------------
@Repository(value = "boardDAO")
public class BoardDAO {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAO.class);

	@Autowired
	SqlSession sqlSession;

	// ---------------------------------------------------------------------------------
	// 게시판 불러오기
	// ---------------------------------------------------------------------------------
	public List<BoardVO> selectBoard(String board_Type) throws DataAccessException {

		logger.info("BoardDAO selectBoard Start");

		List<BoardVO> boardList = null;
		boardList = sqlSession.selectList("mapper.board.BoardByName", board_Type);

		System.out.println(boardList);

		logger.info("BoardDAO selectBoard End");

		return boardList;
	}

	// ---------------------------------------------------------------------------------
	// 게시판 상세보기
	// ---------------------------------------------------------------------------------
	public BoardVO boardDetail(int article_NO) throws DataAccessException {

		logger.info("BoardDAO boardDetail Start");

		BoardVO boardDetail = sqlSession.selectOne("mapper.board.BoardDetail", article_NO);

		System.out.println(boardDetail);

		logger.info("BoardDAO boardDetail End");

		return boardDetail;
	}

	// ---------------------------------------------------------------------------------
	// 게시판 작성
	// ---------------------------------------------------------------------------------
	public void boardWrite(BoardVO vo) throws DataAccessException {

		logger.info("BoardDAO boardDetail Start");

		sqlSession.insert("mapper.board.BoardWriting", vo);

		logger.info("BoardDAO boardDetail End");
	}

	// ---------------------------------------------------------------------------------
	// 게시글 삭제
	// ---------------------------------------------------------------------------------
	public void boardDelete(int article_NO) throws DataAccessException {
		logger.info("BoardDAO boardDelete start");

		sqlSession.delete("mapper.board.boardDelete", article_NO);

		logger.info("BoardDAO boardDelete End");
	}

	// ---------------------------------------------------------------------------------
	// 게시글 수정
	// ---------------------------------------------------------------------------------
	public void boardModify(BoardVO mo) throws DataAccessException {
		logger.info("BoardDAO boardModify start");

		sqlSession.update("mapper.board.boardModify", mo);

		logger.info("BoardDAO boardModify End");
	}

	// ---------------------------------------------------------------------------------
	// 게시글 페이징 검색
	// ---------------------------------------------------------------------------------

	public List<BoardVO> boardPaging(String board_Type, int displayPost, int postNum , String searchType, String keyword) throws Exception {

		logger.info("BoardDAO boardPaging start");
		
		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		data.put("board_Type", board_Type);
		
		 data.put("searchType", searchType);
		  data.put("keyword", keyword);
			
		logger.info("BoardDAO boardPaging End");

		return sqlSession.selectList("mapper.board.boardPaging", data);
				
	}

	// ---------------------------------------------------------------------------------
	// 게시글 총 개수
	// ---------------------------------------------------------------------------------

	public int count(String board_Type, String searchType, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		 data.put("board_Type", board_Type);
		 data.put("searchType", searchType);
		 data.put("keyword", keyword);

		return sqlSession.selectOne("mapper.board.count", data);
	}
	
	//이 아래는 댓글 관련 입니다 
	
	// 댓글 조회
	
	public List<ReplyVO> replylist(int bno) throws Exception {
		
		
		
		return sqlSession.selectList("mapper.board.replyList", bno);
	}

	// 댓글 작성
	
	public void replywrite(ReplyVO vo) throws Exception {
		
		
		
		sqlSession.insert("mapper.board.replyWrite", vo);
	}

	// 댓글 수정
	
	public void replymodify(ReplyVO vo) throws Exception {
		
		
		
		sqlSession.update("mapper.board.replyModify", vo);
	}

	// 댓글 삭제
	
	public void replydelete(ReplyVO vo) throws Exception {
		
		
		int result = sqlSession.delete("mapper.board.replyDelete", vo);
		System.out.println(result);
		
	}

}
