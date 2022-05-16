package com.example.back.workbook.domain;


import com.example.back.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkbookQuestionRepository extends JpaRepository<User, Long> {
}
