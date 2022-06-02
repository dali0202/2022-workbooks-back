package com.example.back.workbook.api;

import com.example.back.auth.CurrentUser;
import com.example.back.user.domain.User;
import com.example.back.workbook.dto.*;
import com.example.back.workbook.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    public ResponseEntity<List<WorkbookResponse>> findWorkbooks(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int lastWorkbookId, @RequestParam @Max(100) int size) {
        return ResponseEntity.ok(workbookService.findByCondition(keyword, lastWorkbookId, size));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<WorkbookDetailResponse> findWorkbook(@PathVariable Long id) {
        return ResponseEntity.ok(workbookService.findWorkbook(id));
    }
    @PostMapping("/mock")
    public ResponseEntity<?> createMock(@CurrentUser User user, @RequestBody @Valid MockRequest mockRequest) {
        workbookService.saveMock(user, mockRequest);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
    @PostMapping("/range")
    public ResponseEntity<?> createRange(@CurrentUser User user, @RequestBody @Valid RangeRequest rangeRequest) {
        workbookService.saveRange(user, rangeRequest);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
    @PostMapping("/custom")
    public ResponseEntity<?> createCustom(@CurrentUser User user, @RequestBody @Valid CustomRequest customRequest) {
        workbookService.saveCustom(user, customRequest);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
}