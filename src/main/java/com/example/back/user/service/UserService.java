package com.example.back.user.service;

import com.example.back.exception.EntityNotFoundException;
import com.example.back.exception.ErrorCode;
import com.example.back.user.domain.User;
import com.example.back.user.domain.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
