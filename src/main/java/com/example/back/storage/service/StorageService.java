package com.example.back.storage.service;

import com.example.back.storage.domain.StorageRepository;
import com.example.back.storage.dto.StorageResponse;
import com.example.back.user.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class StorageService {

    private final StorageRepository storageRepository;
    public List<StorageResponse> findByUser(User user) {
        return StorageResponse.listOf(storageRepository.findByUser(user));
    }
}
