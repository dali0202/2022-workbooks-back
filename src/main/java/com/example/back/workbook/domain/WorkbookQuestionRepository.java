package com.example.back.workbook.domain;


import com.example.back.storage.domain.Storage;
import com.example.back.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkbookQuestionRepository extends JpaRepository<WorkbookQuestion, Long> {

}
