package com.example.back.user.presentation;


import com.example.back.user.domain.User;
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
	public ResponseEntity<?> register(HttpServletRequest request) {
		User user = jwtUtils.getUserByToken(request);
		//Optinal 공부 후 도입?
		return ResponseEntity.ok(UserResponse.of(user));
	}

//	@PostMapping("/register")
//	public ResponseEntity<?> register(@RequestBody UserRegistRequest userRequest) {
//	}
//
//	@PatchMapping("/modify")
//	public ResponseEntity<?> modify(/* User user */, @RequestBody UserModifyRequest userRequest) {
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//	}
}
