package com.example.back.workbook.dto;

import com.example.back.board.domain.Board;
import com.example.back.workbook.domain.Workbook;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkbookResponse {
	private Long id;
	private String title;
	private String userName;

	private int type;
	private LocalDateTime createdDate;
	@Builder
	public WorkbookResponse(Long id, String title, String userName, int type, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.userName = userName;
		this.type = type;
		this.createdDate = createdDate;
	}

	public static WorkbookResponse of(Workbook workbook) {
		return WorkbookResponse
				.builder()
				.id(workbook.getId())
				.title(workbook.getTitle())
				.userName(workbook.getUser().getName())
				.type(workbook.getType())
				.createdDate(workbook.getCreatedDate())
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
