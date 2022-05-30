package com.example.back.workbook.domain;

import com.example.back.question.domain.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class WorkbookQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "workbook_id")
	private Workbook workbook;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;

	private int num;
	@Builder
	public WorkbookQuestion(Workbook workbook, Question question, int num) {
		this.workbook = workbook;
		this.question = question;
		this.num = num;
	}

	public static void associate(Workbook workbook, List<Question> questions) {
		int num = 1;
		for (Question question : questions) {
			WorkbookQuestion workbookQuestion = WorkbookQuestion
					.builder()
					.workbook(workbook)
					.question(question)
					.num(num)
					.build();
			num ++;
			workbookQuestion.changeWorkbook(workbook);
		}
	}
	private void changeWorkbook(Workbook workbook) {
		workbook.getWorkbookQuestions().add(this);
	}
}
