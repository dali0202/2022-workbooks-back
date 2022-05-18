package com.example.back.workbook.api;

import com.example.back.user.domain.User;
import com.example.back.util.JwtUtils;
import com.example.back.workbook.dto.CustomRequest;
import com.example.back.workbook.dto.MockRequest;
import com.example.back.workbook.dto.RangeRequest;
import com.example.back.workbook.dto.WorkbookResponse;
import com.example.back.workbook.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workbooks")
public class WorkbookController {
    private static final String REDIRECT_URL = "/api/storages";
    private final WorkbookService workbookService;

    private final JwtUtils jwtUtils;
    //private final StorageService storageService;
    @GetMapping
    public ResponseEntity<List<WorkbookResponse>> findWorkbooks() {
        return ResponseEntity.ok(workbookService.findAllWorkbooks());
    }
    @PostMapping("/mock")
    public ResponseEntity<?> createMock(@RequestBody MockRequest mockRequest, HttpServletRequest request) {
        User user = jwtUtils.getUserByToken(request);
        workbookService.saveMock(user, mockRequest);

        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
    @PostMapping("/range")
    public ResponseEntity<?> createRange(@RequestBody RangeRequest rangeRequest, HttpServletRequest request) {
        User user = jwtUtils.getUserByToken(request);
        workbookService.saveRange(user, rangeRequest);
        // addWorkbookInMyStorage(workbookResponse);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }

    @PostMapping("/custom")
    public ResponseEntity<?> createCustom(@RequestBody CustomRequest customRequest, HttpServletRequest request) {
        User user = jwtUtils.getUserByToken(request);
        workbookService.saveCustom(user, customRequest);
        // addWorkbookInMyStorage(workbookResponse);
        return ResponseEntity.created(URI.create(REDIRECT_URL)).build();
    }
}