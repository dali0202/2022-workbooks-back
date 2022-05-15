package com.example.back.board.api;

import com.example.back.board.dto.BoardRequest;
import com.example.back.board.dto.BoardResponse;
import com.example.back.board.service.BoardService;
import com.example.back.user.domain.User;
import com.example.back.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
	private final BoardService boardService;
	private final JwtUtils jwtUtils;
	private static final String REDIRECT_URL = "/api/boards/%d";
	@GetMapping
	public ResponseEntity<List<BoardResponse>> findBoards() {
		return ResponseEntity.ok(boardService.findAllBoards());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BoardResponse> findBoard(@PathVariable Long id) {
		return ResponseEntity.ok(boardService.findBoard(id));
	}

	@PostMapping("/write")
	public ResponseEntity<?> createBoard(HttpServletRequest request, @RequestBody BoardRequest boardRequest) {
		User user = jwtUtils.getUserByToken(request);
		Long boardId = boardService.save(user, boardRequest);
		String redirectUrl = String.format(REDIRECT_URL, boardId);

		return ResponseEntity.created(URI.create(redirectUrl)).build();
	}
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