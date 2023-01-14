package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
   
   @Inject
   private final BoardDAO boardDAO;

   @Override
   public int createBoard(BoardVO board) {
      
      return 0;
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
      // TODO Auto-generated method stub
      return null;
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
