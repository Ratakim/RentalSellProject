package com.project.billyus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.billyus.service.BoardService;
import com.project.billyus.vo.BoardVO;
import com.project.billyus.vo.ReplyVO;

//---------------------------------------------------------------------------------
// 게시판 컨트롤러 BoardController
//---------------------------------------------------------------------------------
@Controller(value = "boardController")
@RequestMapping(value = "/board/")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService boardService;
	

//------------------------------------- 페이지 맵핑 .GO Page --------------------------------------------------

	// ---------------------------------------------------------------------------------
	// 게시판 메인, 카테고리 이동
	// ---------------------------------------------------------------------------------
	@RequestMapping(value = "/boardAbout.go")
	public ModelAndView boardAbout(@RequestParam(value = "num", required = false, defaultValue = "1") int num, 
			@RequestParam(value = "val", required = false, defaultValue = "공지") String val,
			@RequestParam(value = "searchType",required = false, defaultValue = "title") String searchType,
		    @RequestParam(value = "keyword",required = false, defaultValue = "") String keyword,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("BoardController myPageAbout Start");
		
		//게시물 총 개수
		int count = boardService.count(val,searchType, keyword);
		//System.out.println("count" + count);
		//System.out.println(num);
		
		//한 페이지에 출력할 게시물 개수
		int postNum = 10;
		
		//하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
		int pageNum = (int)Math.ceil((double)count/postNum);
		
		// 출력할 게시물
	    int displayPost = (num - 1) * postNum + 1;
	    //System.out.println(displayPost);
	    //한번에 표시할 페이징 번호의 개수
	    int pageNum_cnt = 5;
	    
	    //표시되는 페이지 번호 중 마지막 번호
	    int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
	    //System.out.println("endPageNum = " + endPageNum);
	    // 표시되는 페이지 번호 중 첫번째 번호
	    int startPageNum = endPageNum - (pageNum_cnt - 1);
	    //System.out.println("startPageNum = " + startPageNum);
	    // 마지막 번호 재계산
	    int endPageNum_tmp = (int)(Math.ceil((double)count / (double)(pageNum_cnt*2)));
	    // System.out.println("endPageNum_tmp = " + endPageNum_tmp);
	     
	    if(endPageNum > endPageNum_tmp) {
	     endPageNum = endPageNum_tmp;
	    }
	    
	    boolean prev = startPageNum == 1 ? false : true;
	    boolean next = endPageNum * (pageNum_cnt * 2 ) >= count ? false : true;
	    //System.out.println(prev + ", " + next);
				
		List<BoardVO> boardPaging = boardService.boardPaging(val, displayPost, postNum * num, searchType, keyword);

		ModelAndView mav = new ModelAndView("/board/boardAbout");		
		
		mav.addObject("boardPaging", boardPaging);
		mav.addObject("pageNum",pageNum);
		
		// 시작 및 끝 번호
		mav.addObject("startPageNum", startPageNum);
		mav.addObject("endPageNum", endPageNum);

		// 이전 및 다음 
		mav.addObject("prev", prev);
		mav.addObject("next", next);
		
		// 현재 페이지
		mav.addObject("select", num);
		
		mav.addObject("count", count);

		logger.info("BoardController myPageAbout End");

		return mav;
	}

	// ---------------------------------------------------------------------------------
	// 게시판 상세보기
	// ---------------------------------------------------------------------------------
	@RequestMapping(value = "/boardDetail.go")
	public ModelAndView boardDetail(
			
			@RequestParam("article_NO") int article_NO,
			@RequestParam(value = "val", required = false, defaultValue = "공지") String val, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("BoardController boardDetail Start");

		BoardVO boardDetail = null;
		boardDetail = boardService.boardDetail(article_NO);
		//System.out.println(boardDetail);

		ModelAndView mav = new ModelAndView("/board/boardDetail");

		mav.addObject("boardDetail", boardDetail);
		
		// 댓글 조회
		List<ReplyVO> reply = boardService.replylist(article_NO);
		 mav.addObject("reply", reply);

		logger.info("BoardController boardDetail End");

		return mav;
	}
	
	// ---------------------------------------------------------------------------------
	// 게시판 댓글 작성
	// ---------------------------------------------------------------------------------
	@PostMapping(value = "/boardWrite.do")
	public ModelAndView boardWrite(ReplyVO vo,
			@RequestParam(value = "val", required = false, defaultValue = "공지") String val, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("BoardController boardWrite Start");
		
		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");
		System.out.println(user_ID);
		vo.setId(user_ID);
		
		boardService.replywrite(vo);

		
		ModelAndView mav = new ModelAndView("redirect:/board/boardDetail.go?article_NO=" +vo.getBno());


		logger.info("BoardController boardWrite End");

		return mav;
	}
	
	// ---------------------------------------------------------------------------------
	// 댓글 삭제
	// ---------------------------------------------------------------------------------

	@RequestMapping("/ReplyDelete.go")
		public ModelAndView boardReplyDelete(ReplyVO vo, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			int bno = vo.getBno();
			boardService.replydelete(vo);
			
			System.out.println(vo.getBno());
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/board/boardDetail.go?article_NO=" + bno);

			return mav;
	}

	// ---------------------------------------------------------------------------------
	// 게시글 작성 get
	// ---------------------------------------------------------------------------------
	@GetMapping(value = "/boardWriting.go")
	public ModelAndView myPageWriting(HttpServletRequest request, HttpServletResponse response)
			throws DataAccessException {

		logger.info("BoardController myPageWriting Start");

		ModelAndView mav = new ModelAndView("/board/boardWriting");

		logger.info("BoardController myPageWriting End");

		return mav;
	}

	// ---------------------------------------------------------------------------------
	// 게시글 작성 post
	// ---------------------------------------------------------------------------------
	@PostMapping("/boardWriting.go")
	public ModelAndView postwrite(BoardVO vo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String user_ID = (String) session.getAttribute("userInfoId");
		System.out.println(user_ID);
		vo.setId(user_ID);
		
		boardService.boardWrite(vo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/boardAbout.go");

		return mav;

	}
	// ---------------------------------------------------------------------------------
	// 게시글 삭제
	// ---------------------------------------------------------------------------------

	@RequestMapping("/boardDelete.go")
	public ModelAndView boardDelete(int article_NO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		boardService.boardDelete(article_NO);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/boardAbout.go");

		return mav;

	}
	// ---------------------------------------------------------------------------------
	// 게시글 수정 GET
	// ---------------------------------------------------------------------------------

	@GetMapping("/boardModify.go")
	public ModelAndView boardModify(int article_NO,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BoardVO boardDetail = boardService.boardDetail(article_NO);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/boardModify");
		mav.addObject("boardDetail", boardDetail);

		return mav;

	}
	//----------------------------------------
	// 게시글 수정 Post
	//----------------------------------------
	@PostMapping("/boardModify.go")
	public ModelAndView getModify(BoardVO mo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		boardService.boardModify(mo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/boardDetail.go?article_NO="+mo.getArticle_NO());

		return mav;

	}

//------------------------------------- 페이지 맵핑 .GO Page - END --------------------------------------------------

//------------------------------------- 페이지 기능 맵핑 .do Page --------------------------------------------------

	// ---------------------- ajax ---------------------------

	// -------------------- ajax - END -----------------------

	// -------------------- .do 컨트롤러 ---------------------------

	// -------------------- .do 컨트롤러 - END -----------------------

//------------------------------------- 페이지 기능 맵핑 .do Page - END --------------------------------------------------

}
