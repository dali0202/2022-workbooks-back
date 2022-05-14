package com.example.back.board.api;

import com.example.back.board.dto.BoardResponse;
import com.example.back.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
	private final BoardService boardService;

	@GetMapping
	public ResponseEntity<List<BoardResponse>> findBoards() {
		return ResponseEntity.ok(boardService.findAllBoards());
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<BoardResponse> findBoard(@PathVariable Long id) {
//		BoardResponseDto responseDto = boardService.read(id);
//		return ResponseEntity.ok(BoardAssembler.toBoardResponse(responseDto));
//	}
//
//	@PostMapping("/write")
//	public ResponseEntity<?> createBoard(@CurrentUser User user, @RequestBody BoardRequest boardRequest) {
//		Long boardId = boardService.write(BoardAssembler.boardRequestDto(user, boardRequest));
//		String redirectUrl = String.format(REDIRECT_URL, boardId);
//
//		return ResponseEntity.created(URI.create(redirectUrl)).build();
//	}
//
//	@PatchMapping("/edit/{id}")
//	public ResponseEntity<?> updateBoard(@CurrentUser User user, @PathVariable Long id, @RequestBody BoardRequest boardRequest) {
//		boardService.update(BoardAssembler.boardUpdateRequestDto(user, id, boardRequest));
//		String redirectUrl = String.format(REDIRECT_URL, id);
//
//		return ResponseEntity.created(URI.create(redirectUrl)).build();
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deleteBoard(@CurrentUser User user, @PathVariable Long id) {
//		boardService.delete(user, id);
//
//		return ResponseEntity.noContent().build();
//	}
}