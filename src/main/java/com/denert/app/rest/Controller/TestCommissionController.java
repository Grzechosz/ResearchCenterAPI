package com.denert.app.rest.Controller;

import com.denert.app.rest.Models.TestCommission;
import com.denert.app.rest.Repo.TestCommissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCommissionController {

    @Autowired
    private TestCommissionRepo testCommissionRepo;

    @GetMapping(value = "/test-commissions")
    public List<TestCommission> getCommissions() {
        return testCommissionRepo.findAll();
    }

    @Transactional
    @PostMapping(value = "/test-commissions")
    public ResponseEntity<TestCommission> saveTestCommission(@RequestBody TestCommission testCommission) {
        testCommissionRepo.save(testCommission);
        return ResponseEntity.ok(testCommission);
    }
}
