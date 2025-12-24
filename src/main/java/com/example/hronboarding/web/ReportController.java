package com.example.hronboarding.web;

import com.example.hronboarding.observer.ReportObserver;
import com.example.hronboarding.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportObserver reportObserver;

    @GetMapping("/latest")
    public ResponseEntity<Report> latest() {
        return ResponseEntity.ok(reportObserver.getLatestReport());
    }
}
