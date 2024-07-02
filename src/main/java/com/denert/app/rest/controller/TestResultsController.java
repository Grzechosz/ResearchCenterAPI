package com.denert.app.rest.controller;

import com.denert.app.rest.dto.TestResultsRequest;
import com.denert.app.rest.dto.TestResultsResponse;
import com.denert.app.rest.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class TestResultsController {

    private final TestResultService testResultsService;

    @GetMapping
    public List<TestResultsResponse> getResults() {
        return testResultsService.getAllTestResults();
    }

    @GetMapping(value = "/{id}")
    public TestResultsResponse getResults(@PathVariable long id) {
        return testResultsService.getTestResultsById(id);
    }

    @PostMapping
    public ResponseEntity<TestResultsResponse> saveResults(@RequestBody TestResultsRequest testResultsRequest) {
        TestResultsResponse savedTestResults = testResultsService.createTestResults(testResultsRequest);
        return ResponseEntity.ok(savedTestResults);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TestResultsResponse> updateResults(@PathVariable long id, @RequestBody TestResultsRequest testResultsRequest) {
        TestResultsResponse updatedTestResults = testResultsService.updateTestResults(testResultsRequest, id);
        return ResponseEntity.ok(updatedTestResults);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteResults(@PathVariable long id) {
        testResultsService.deleteTestResults(id);
        return ResponseEntity.ok("Deleted test result with id: " + id);
    }
}
