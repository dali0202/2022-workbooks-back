package com.example.back.workbook.api;

import com.example.back.auth.auth.api.CurrentUser;
import com.example.back.auth.auth.domain.LoginUser;
import com.example.back.user.domain.User;
import com.example.back.user.service.UserService;
import com.example.back.workbook.dto.*;
import com.example.back.workbook.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workbooks")
public class WorkbookController {
    private static final String REDIRECT_URL = "/api/storages";
    private final WorkbookService workbookService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<WorkbookResponse>> findWorkbooks(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int lastWorkbookId, @RequestParam @Max(100) int size) {
        return ResponseEntity.ok(workbookService.findByCondition(keyword, lastWorkbookId, size));
    }
    @GetMapping("/{id}")
    public ResponseEntity<WorkbookDetailResponse> findWorkbook(@PathVariable Long id) {
        return ResponseEntity.ok(workbookService.findWorkbook(id));
    }
    @PostMapping("/mock")
    public ResponseEntity<?> createMock(@CurrentUser LoginUser loginUser, @RequestBody @Valid MockRequest mockRequest) {
        User user = userService.getById(loginUser.getId());
        workbookService.saveMock(user, mockRequest);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
    @PostMapping("/range")
    public ResponseEntity<?> createRange(@CurrentUser LoginUser loginUser, @RequestBody @Valid RangeRequest rangeRequest) {
        User user = userService.getById(loginUser.getId());
        workbookService.saveRange(user, rangeRequest);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
    @PostMapping("/custom")
    public ResponseEntity<?> createCustom(@CurrentUser LoginUser loginUser, @RequestBody @Valid CustomRequest customRequest) {
        User user = userService.getById(loginUser.getId());
        workbookService.saveCustom(user, customRequest);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
}