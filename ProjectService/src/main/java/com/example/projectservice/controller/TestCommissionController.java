package com.example.projectservice.controller;

import com.example.projectservice.dto.TestCommissionRequest;
import com.example.projectservice.dto.TestCommissionResponse;
import com.example.projectservice.service.TestCommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-commissions")
@RequiredArgsConstructor
public class TestCommissionController {

    private final TestCommissionService testCommissionService;

    @GetMapping
    public List<TestCommissionResponse> getCommissions() {
        return testCommissionService.getAllTestCommissions();
    }

    @GetMapping(value = "/{id}")
    public TestCommissionResponse getCommission(@PathVariable long id) {
        return testCommissionService.getTestCommissionById(id);
    }

    @PostMapping
    public ResponseEntity<TestCommissionResponse> saveTestCommission(@RequestBody TestCommissionRequest testCommissionRequest) {
        TestCommissionResponse savedTestCommission = testCommissionService.createTestCommission(testCommissionRequest);
        return ResponseEntity.ok(savedTestCommission);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TestCommissionResponse> updateTestCommission(@PathVariable long id, @RequestBody TestCommissionRequest testCommissionRequest) {
        TestCommissionResponse updatedTestCommission = testCommissionService.updateTestCommission(testCommissionRequest, id);
        return ResponseEntity.ok(updatedTestCommission);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCommission(@PathVariable long id) {
        testCommissionService.deleteTestCommission(id);
        return ResponseEntity.ok("Deleted test commission with id: " + id);
    }
}
