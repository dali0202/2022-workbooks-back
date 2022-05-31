package com.example.back.user.api;


import com.example.back.user.domain.User;
import com.example.back.user.dto.UserResponse;
import com.example.back.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	private final JwtUtils jwtUtils;

	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
		User user = jwtUtils.getUserByToken(request);
		return ResponseEntity.ok(UserResponse.of(user));
	}
}
