package com.bluefarid.scrooge.app.box.controller;

import com.bluefarid.scrooge.app.box.request.FinanceRequest;
import com.bluefarid.scrooge.app.box.request.LoginRequest;
import com.bluefarid.scrooge.app.box.request.OtpRequest;
import com.bluefarid.scrooge.app.box.response.FinanceResponse;
import com.bluefarid.scrooge.app.box.response.LoginResponse;
import com.bluefarid.scrooge.app.box.service.SnappBoxService;
import com.bluefarid.scrooge.response.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/box")
@RequiredArgsConstructor
public class SnappBoxController {
    private final SnappBoxService service;

    @PostMapping("/otp")
    public ResponseEntity<Void> otp(@RequestBody OtpRequest request) {
        service.otp(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/history")
    public ResponseEntity<HistoryResponse> history(FinanceRequest request) {
        return ResponseEntity.ok(service.finance(request));
    }
}
