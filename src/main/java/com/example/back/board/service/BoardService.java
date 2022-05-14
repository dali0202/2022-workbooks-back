package com.example.back.board.service;

import com.example.back.board.domain.Board;
import com.example.back.board.domain.BoardRepository;
import com.example.back.board.dto.BoardDetailResponse;
import com.example.back.board.dto.BoardResponse;
import com.example.back.exception.EntityNotFoundException;
import com.example.back.exception.ErrorCode;
import com.example.back.user.domain.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	@Transactional
	public List<BoardResponse> findAllBoards() {
		return BoardResponse.listOf(boardRepository.findAll());
	}

//	@Transactional
//	public BoardDetailResponse findBoard(Long id) {
//		Board board = findBoardById(id);
//		return BoardDetailResponse.of(board);
//	}

//	@Transactional
//	public Long write(BoardRequestDto boardRequestDto) {
//		Board savedBoard = boardRepository.save(createBoard(boardRequestDto));
//		return savedBoard.getId();
//	}
//
//	@Transactional
//	public Long update(BoardUpdateRequestDto boardUpdateRequestDto) {
//		Board board = findBoardById(boardUpdateRequestDto.getBoardId());
//		Workbook workbook = findWorkbookById(boardUpdateRequestDto.getWorkbookId());
//
//		if (board.getUser().getId() != boardUpdateRequestDto.getUserId()) {
//			throw new AuthException(ErrorCode.BOARD_UNAUTHORIZED);
//		}
//
//		board.update(boardUpdateRequestDto, workbook);
//
//		return boardUpdateRequestDto.getBoardId();
//	}
//
//	@Transactional
//	public void delete(User user, Long id) {
//		Board board = findBoardById(id);
//
//		if (board.getUser() != user) {
//			throw new AuthException(ErrorCode.BOARD_UNAUTHORIZED);
//		}
//
//		boardRepository.delete(board);
//	}
//
//	private Board createBoard(BoardRequestDto boardRequestDto) {
//		User user = findUserById(boardRequestDto.getUserId());
//		Workbook workbook = findWorkbookById(boardRequestDto.getWorkbookId());
//		Board board = BoardDtoAssembler.board(user, workbook, boardRequestDto);
//		return board;
//	}
//
//	private User findUserById(Long id) {
//		return userRepository.findById(id)
//			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
//	}
//
//	private Workbook findWorkbookById(Long id) {
//		return workbookRepository.findById(id)
//			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.WORKBOOK_NOT_FOUND));
//	}

	private Board findBoardById(Long id) {
		return boardRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.BOARD_NOT_FOUND));
	}
}