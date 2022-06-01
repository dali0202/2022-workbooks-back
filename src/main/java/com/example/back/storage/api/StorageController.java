package com.example.back.storage.api;

import com.example.back.auth.CurrentUser;
import com.example.back.storage.dto.StorageResponse;
import com.example.back.storage.service.StorageService;
import com.example.back.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
public class StorageController {
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<StorageResponse>> findStorage(@CurrentUser User user) {
        return ResponseEntity.ok(storageService.findByUser(user));
    }
}
