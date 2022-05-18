package com.example.back.workbook.dto;

import com.example.back.board.domain.Board;
import com.example.back.workbook.domain.Workbook;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkbookResponse {
	private Long id;
	private String title;
	private String userName;
	@Builder
	public WorkbookResponse(Long id, String title, String userName) {
		this.id = id;
		this.title = title;
		this.userName = userName;
	}

	public static WorkbookResponse of(Workbook workbook) {
		return WorkbookResponse
				.builder()
				.id(workbook.getId())
				.title(workbook.getTitle())
				.userName(workbook.getUser().getName())
				.build();
	}
	public static List<WorkbookResponse> listOf(List<Workbook> workbooks) {
		List<WorkbookResponse> workbookResponses = new ArrayList<>();

		for (Workbook workbook : workbooks) {
			workbookResponses.add(of(workbook));
		}

		return workbookResponses;
	}
}
