package com.example.back.user.api;


import com.example.back.auth.CurrentUser;
import com.example.back.user.domain.User;
import com.example.back.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(@CurrentUser User user) {
		return ResponseEntity.ok(UserResponse.of(user));
	}
}
