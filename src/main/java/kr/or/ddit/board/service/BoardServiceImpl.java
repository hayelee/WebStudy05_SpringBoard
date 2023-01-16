package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.exception.NotExistBoardException;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
   
   @Inject
   private final BoardDAO boardDAO;
   @Inject
   private AttatchDAO attatchDAO;
   @Inject
   private PasswordEncoder encoder;
   
   @Value("#{appInfo.saveFiles}")
   private File saveFiles;
   
   @PostConstruct
   public void init() throws IOException {
	   log.info("EL로 주입된 첨부파일 저장 경로 : {}", saveFiles.getCanonicalPath());
   }
   
   private int processAttatchList(BoardVO board) {
	   List<AttatchVO> attatchList = board.getAttatchList();
	   if(attatchList==null || attatchList.isEmpty()) return 0;
	   // 1. metadate 저장 - DB (ATTATCH)
	   int rowcnt = attatchDAO.insertAttatches(board);
	   // 2. binary 저장 - Middle Tier : (D:\saveFiles)
	   try {
		   for(AttatchVO attatch : attatchList) {
//			   if(1==1)
//				   throw new RuntimeException("강제 발생 예외");
			   attatch.saveTo(saveFiles);
		   }
		   return rowcnt;
	   }catch (IOException e) {
		   throw new RuntimeException(e);
	   }
   }
   
   @Transactional
   @Override
   public int createBoard(BoardVO board) {
	  String plain = board.getBoPass();
	  String encoded = encoder.encode(plain);
	  board.setBoPass(encoded);
	  int rowcnt = boardDAO.insertBoard(board);
	  // 첨부 파일 등록
	  rowcnt += processAttatchList(board);
	  return rowcnt;
   }
   @Override
   public void retrieveBoardList(PagingVO<BoardVO> pagingVO) {
      pagingVO.setTotalRecord(boardDAO.selectTotalRecord(pagingVO));
      
      List<BoardVO> boardList = boardDAO.selectBoardList(pagingVO);
      pagingVO.setDataList(boardList);
      log.info("boardList : {}",boardList);
      boardList.stream().forEach(System.out::println);
   }

   @Override
   public BoardVO retrieveBoard(int boNo) {
	   BoardVO board = boardDAO.selectBoard(boNo);
	   if(board==null) {
		   throw new NotExistBoardException(boNo);
	   }
	   boardDAO.incrementHit(boNo);
	   return board;
   }

   @Override
   public int modifyBoard(BoardVO board) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int removeBoard(int boNo) {
      // TODO Auto-generated method stub
      return 0;
   }

//   @Override
//   public AttatchVO retrieveFordownload(int attNo) {
//      // TODO Auto-generated method stub
//      return null;
//   }

@Override
public AttatchVO retrieveForDownload(int attNo) {
	// TODO Auto-generated method stub
	return null;
}

}
