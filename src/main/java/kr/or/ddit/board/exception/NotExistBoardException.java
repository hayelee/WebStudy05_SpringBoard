package kr.or.ddit.board.exception;

public class NotExistBoardException extends RuntimeException{

	public NotExistBoardException(int boNo) { // 몇번글이 포함되지 않았다는 걸 보여줌
		super(String.format("%d 번 글은 존재하지 않습니다.", boNo));
	}
	
}
