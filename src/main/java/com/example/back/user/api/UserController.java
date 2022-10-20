package com.example.back.user.api;


import com.example.back.auth.auth.api.CurrentUser;
import com.example.back.auth.auth.domain.LoginUser;
import com.example.back.user.dto.UserResponse;
import com.example.back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(@CurrentUser LoginUser loginUser) {
		return ResponseEntity.ok(UserResponse.of(userService.getById(loginUser.getId())));
	}
}
