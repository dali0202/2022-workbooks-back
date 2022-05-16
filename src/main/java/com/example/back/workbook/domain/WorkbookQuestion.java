package com.example.back.workbook.domain;

import com.example.back.question.domain.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class WorkbookQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workbook_id")
	private Workbook workbook;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;

	private byte num;
	@Builder
	public WorkbookQuestion(Workbook workbook, Question question, byte num) {
		this.workbook = workbook;
		this.question = question;
		this.num = num;
	}
}
