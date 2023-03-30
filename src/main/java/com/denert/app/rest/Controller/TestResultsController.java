package com.denert.app.rest.Controller;

import com.denert.app.rest.Models.Address;
import com.denert.app.rest.Models.Project;
import com.denert.app.rest.Models.TestResults;
import com.denert.app.rest.Repo.ProjectRepo;
import com.denert.app.rest.Repo.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestResultsController {

    @Autowired
    private ResultRepo resultRepo;

    @GetMapping(value = "/results")
    public List<TestResults> getResults() {
        return resultRepo.findAll();
    }

    @PostMapping(value = "/results")
    public ResponseEntity<TestResults> saveResults(@RequestBody TestResults testResults) {
        resultRepo.save(testResults);
        return ResponseEntity.ok(testResults);
    }

    @PutMapping(value = "/results/{id}")
    public ResponseEntity<TestResults> updateResults(@PathVariable long id, @RequestBody TestResults testResults) {
        TestResults updatedResult = resultRepo.findById(id).get();
        updatedResult.setResultsDesc(testResults.getResultsDesc());
        updatedResult.setTestCommission(testResults.getTestCommission());
        resultRepo.save(updatedResult);
        return ResponseEntity.ok(updatedResult);
    }
}
