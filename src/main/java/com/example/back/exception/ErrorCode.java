package com.example.back.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	//Common
	INVALID_INPUT_VALUE(400, "C_001", "적절하지 않은 요청 값입니다."),
	BOARD_NOT_FOUND(404, "BO_001", "게시글을 찾을 수 없습니다."),
	BOARD_UNAUTHORIZED(403, "BO_002", "게시글 수정 권한이 없습니다"),

	STORAGE_NOT_FOUND(404, "ST_001", "보관함을 찾을 수 없습니다."),
	STORAGE_UNAUTHORIZED(403, "ST_002", "보관함 수정 권한이 없습니다"),

	USER_NOT_FOUND(404, "US_001", "유저를 찾을 수 없습니다."),
	//WORKBOOK
	WORKBOOK_NOT_FOUND(404, "WO_001", "문제집을 찾을 수 없습니다."),

	//AUTH
	AUTH_ERROR(401, "AU_001", "인증 관련 오류가 발생했습니다."),
	UNAUTHORIZED_REDIRECT_URI(401, "AU_002", "인증되지 않은 REDIRECT_URI 입니다."),
	ACCESS_TOKEN_NOT_FOUND(401, "AU_003", "엑세스 토큰이 존재하지 않습니다."),
	ACCESS_TOKEN_EXPIRED(401, "AU_004", "엑세스 토큰이 만료되었습니다."),
	ACCESS_TOKEN_NOT_VALID(401, "AU_005", "엑세스 토큰의 서명이 일치하지 않습니다."),

	//OAUTH2
	OAUTH2_EMAIL_NOT_EXIST(404, "OA_001", "이메일 리소스가 존재하지 않습니다."),
	OAUTH2_DUPLICATE_EMAIL(400, "OA_002", "다른 소셜로그인에서 이미 사용된 이메일입니다."),
	OAUTH2_PROVIDER_NOT_EXIST(400, "OA_003", "프로바이더가 존재하지 않습니다.");

	private final String code;
	private final String message;
	private final int status;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
