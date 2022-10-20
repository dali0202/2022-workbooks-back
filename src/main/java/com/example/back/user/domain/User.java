package com.example.back.user.domain;


import com.example.back.common.domain.BaseTimeEntity;
import com.example.back.auth.auth.domain.AuthProvider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	private String imageUrl;

	@Enumerated(value = EnumType.STRING)
	private Role role;

	@Enumerated(value = EnumType.STRING)
	private AuthProvider authProvider;

	@Builder
	public User(String name, String email, String imageUrl, Role role, AuthProvider provider) {
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		this.role = role;
		this.authProvider = provider;
	}

	public void update(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
	}
}

