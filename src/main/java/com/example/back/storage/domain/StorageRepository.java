package com.example.back.storage.domain;


import com.example.back.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByUser(User user);
}
