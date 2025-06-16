package com.example.projectservice.service;

import com.example.projectservice.dto.TestCommissionRequest;
import com.example.projectservice.dto.TestCommissionResponse;
import com.example.projectservice.exception.TestCommissionNotFoundException;
import com.example.projectservice.models.TestCommission;
import com.example.projectservice.repo.TestCommissionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestCommissionService {
    private final TestCommissionRepo testCommissionRepo;

    public TestCommissionResponse createTestCommission(TestCommissionRequest testCommissionRequest){
        final TestCommission testCommission = TestCommission.builder()
                .user(testCommissionRequest.getUser())
                .project(testCommissionRequest.getProject())
                .date(testCommissionRequest.getDate())
                .description(testCommissionRequest.getDescription())
                .testResult(testCommissionRequest.getTestResult())
                .build();
        TestCommissionResponse testCommissionResponse = mapToTestCommissionResponse(testCommissionRepo.save(testCommission));
        log.info("TestCommission {} is saved", testCommission.getCommissionId());

        return testCommissionResponse;
    }

    public List<TestCommissionResponse> getAllTestCommissions(){
        List<TestCommission> testCommissions = testCommissionRepo.findAll();

        return testCommissions.stream().map(this::mapToTestCommissionResponse).toList();
    }

    public TestCommissionResponse getTestCommissionById(long id){
        return testCommissionRepo.findById(id)
                .map(this::mapToTestCommissionResponse)
                .orElseThrow(() -> new TestCommissionNotFoundException("Test Commission with ID " + id + " not found."));
    }

    public TestCommissionResponse updateTestCommission(TestCommissionRequest testCommissionRequest, long id) {
        return testCommissionRepo.findById(id)
                .map(existingTestCommission -> {
                    existingTestCommission.setProject(testCommissionRequest.getProject());
                    existingTestCommission.setUser(testCommissionRequest.getUser());
                    existingTestCommission.setDate(testCommissionRequest.getDate());
                    existingTestCommission.setDescription(testCommissionRequest.getDescription());
                    existingTestCommission.setTestResult(testCommissionRequest.getTestResult());

                    TestCommission updatedTestCommission = testCommissionRepo.save(existingTestCommission);
                    log.info("Test Commission {} was updated", updatedTestCommission.getCommissionId());
                    return mapToTestCommissionResponse(updatedTestCommission);

                })  .orElseThrow(() -> new TestCommissionNotFoundException("Test Commission with ID " + id + " not found"));
    }

    public void deleteTestCommission(long id){
        TestCommission testCommissionToDelete = testCommissionRepo.findById(id)
                .orElseThrow(() -> new TestCommissionNotFoundException("Test Commission with ID " + id + " not found."));

        testCommissionRepo.delete(testCommissionToDelete);
        log.info("Test Commission {} was deleted", id);
    }

    private TestCommissionResponse mapToTestCommissionResponse(TestCommission testCommission) {
        return TestCommissionResponse.builder()
                .commissionId(testCommission.getCommissionId())
                .user(testCommission.getUser())
                .project(testCommission.getProject())
                .date(testCommission.getDate())
                .description(testCommission.getDescription())
                .testResult(testCommission.getTestResult())
                .build();
    }
}

