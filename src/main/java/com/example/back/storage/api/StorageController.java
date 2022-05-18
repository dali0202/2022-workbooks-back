package com.example.back.storage.api;

import com.example.back.storage.dto.StorageResponse;
import com.example.back.storage.service.StorageService;
import com.example.back.user.domain.User;
import com.example.back.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
public class StorageController {
    private final StorageService storageService;
    private final JwtUtils jwtUtils;
    @GetMapping
    public ResponseEntity<List<StorageResponse>> findStorage(HttpServletRequest request) {
        User user = jwtUtils.getUserByToken(request);
        return ResponseEntity.ok(storageService.findByUser(user));
    }
}
