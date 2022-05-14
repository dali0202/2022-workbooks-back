package com.example.back.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

	BOARD_NOT_FOUND(400, "BO_001", "게시글을 찾을 수 없습니다."),
	BOARD_UNAUTHORIZED(403, "BO_002", "게시글 수정 권한이 없습니다"),

	STORAGE_NOT_FOUND(400, "ST_001", "보관함을 찾을 수 없습니다."),
	STORAGE_UNAUTHORIZED(403, "ST_002", "보관함 수정 권한이 없습니다"),

	USER_NOT_FOUND(400, "US_001", "유저를 찾을 수 없습니다."),

	WORKBOOK_NOT_FOUND(400, "WO_001", "문제집을 찾을 수 없습니다."),

	AUTH_ERROR(400, "AU_001", "인증 관련 오류가 발생했습니다."),
	UNAUTHORIZED_REDIRECT_URI(403, "AU_002", "인증되지 않은 REDIRECT_URI 입니다.");

	private final String code;
	private final String message;
	private final int status;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
