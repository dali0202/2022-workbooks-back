package com.example.back.workbook.api;

import com.example.back.user.domain.User;
import com.example.back.util.JwtUtils;
import com.example.back.workbook.dto.MockRequest;
import com.example.back.workbook.dto.RangeRequest;
import com.example.back.workbook.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workbooks")
public class WorkbookController {
    private static final String REDIRECT_URL = "/api/storages";
    private final WorkbookService workbookService;
    private final JwtUtils jwtUtils;
    //private final StorageService storageService;
    @PostMapping("/mock")
    public ResponseEntity<?> createMock(@RequestBody MockRequest mockRequest, HttpServletRequest request) {
        User user = jwtUtils.getUserByToken(request);
        workbookService.saveMock(user, mockRequest);
        //addWorkbookInMyStorage(user.getId(), workbookId);

        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
    @PostMapping("/range")
    public ResponseEntity<?> createRange(@RequestBody RangeRequest rangeRequest, HttpServletRequest request) {
        User user = jwtUtils.getUserByToken(request);
        workbookService.saveRange(user, rangeRequest);
        // addWorkbookInMyStorage(workbookResponse);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }

//    @PostMapping("/custom")
//    public ResponseEntity<?> createCustom(/* User user */, @RequestBody CustomRequest customRequest) {
//        // WorkbookResponse workbookResponse = workbookService.saveCustom(user, customRequest);
//        // addWorkbookInMyStorage(workbookResponse);
//    }

//    private void addWorkbookInMyStorage(Long userId, Long workbookId) {
//        storageService.save(StorageRequestDto.
//                builder()
//                .userId(userId)
//                .workbookId(workbookId)
//                .build());
//    }
}