package com.example.back.board.dto;

import com.example.back.board.domain.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse {
	private Long id;
	private String name;
	//private Long workbookId;
	private String title;
	private String description;

	@Builder
	public BoardResponse(Long id, String name, /*Long workbookId,*/ String title, String description) {
		this.id = id;
		this.name = name;
		//this.workbookId = workbookId;
		this.title = title;
		this.description = description;
	}

	public static BoardResponse of(Board board) {
		return BoardResponse
				.builder()
				.id(board.getId())
				.name(board.getUser().getName())
				.title(board.getTitle())
				.description(board.getDescription())
				.build();
	}
	public static List<BoardResponse> listOf(List<Board> boards) {
		List<BoardResponse> boardResponses = new ArrayList<>();

		for (Board board : boards) {
			boardResponses.add(of(board));
		}

		return boardResponses;
	}
}
