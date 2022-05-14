package com.example.back.board.domain;


import com.example.back.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // PRIVATE - 불가능. 프록시 이용하기 때문.
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "workbook_id")
//	private Workbook workbook;

	private String title;

	private String description;

	@Builder
	public Board(User user, /*Workbook workbook,*/ String title, String description) {
		this.user = user;
		//this.workbook = workbook;
		this.title = title;
		this.description = description;
	}

//	public void update(BoardUpdateRequestDto requestDto, Workbook workbook) {
//		this.title = requestDto.getTitle();
//		this.description = requestDto.getDescription();
//		this.workbook = workbook;
//	}
}
