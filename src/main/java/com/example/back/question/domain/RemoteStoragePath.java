package com.example.back.question.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class RemoteStoragePath {
	private String descriptionPath;

	private String solutionPath;

	public RemoteStoragePath(String descriptionPath, String solutionPath) {
		this.descriptionPath = descriptionPath;
		this.solutionPath = solutionPath;
	}
}
