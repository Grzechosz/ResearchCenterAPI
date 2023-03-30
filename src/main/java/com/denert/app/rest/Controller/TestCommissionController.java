package com.denert.app.rest.Controller;

import com.denert.app.rest.Models.Agreement;
import com.denert.app.rest.Models.TestCommission;
import com.denert.app.rest.Repo.TestCommissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestCommissionController {

    @Autowired
    private TestCommissionRepo testCommissionRepo;

    @GetMapping(value = "/test-commissions")
    public List<TestCommission> getCommissions() {
        return testCommissionRepo.findAll();
    }

    @GetMapping(value = "/test-commissions/{id}")
    public TestCommission getCommission(@PathVariable long id) {
        return testCommissionRepo.findById(id).get();
    }

    @Transactional
    @PostMapping(value = "/test-commissions")
    public ResponseEntity<TestCommission> saveTestCommission(@RequestBody TestCommission testCommission) {
        testCommissionRepo.save(testCommission);
        return ResponseEntity.ok(testCommission);
    }

    @PutMapping(value = "/test-commissions/{id}")
    public ResponseEntity<TestCommission> updateTestCommission(@PathVariable long id, @RequestBody TestCommission testCommission) {
        TestCommission updateCommission = testCommissionRepo.findById(id).get();

        updateCommission.setDescription(testCommission.getDescription());
        updateCommission.setDate(testCommission.getDate());
        updateCommission.setTestResult(testCommission.getTestResult());
        updateCommission.setProject(testCommission.getProject());
        updateCommission.setUser(testCommission.getUser());

        testCommissionRepo.save(updateCommission);
        return ResponseEntity.ok(updateCommission);
    }

    @DeleteMapping(value = "/test-commissions/{id}")
    public String deleteCommission(@PathVariable long id) {
        TestCommission deleteCommission = testCommissionRepo.findById(id).get();
        testCommissionRepo.delete(deleteCommission);
        return "Deleted test commission with id: "+id;
    }
}
