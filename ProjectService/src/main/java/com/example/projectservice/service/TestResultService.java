package com.example.projectservice.service;

import com.example.projectservice.dto.TestResultsRequest;
import com.example.projectservice.dto.TestResultsResponse;
import com.example.projectservice.exception.TestResultsNotFoundException;
import com.example.projectservice.models.TestResults;
import com.example.projectservice.repo.ResultRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestResultService {
    private final ResultRepo testResultsRepo;

    public TestResultsResponse createTestResults(TestResultsRequest testResultsRequest){
        final TestResults testResults = TestResults.builder()
                .resultsDesc(testResultsRequest.getResultsDesc())
                .build();
        TestResultsResponse testResultsResponse = mapToTestResultsResponse(testResultsRepo.save(testResults));
        log.info("TestResults {} is saved", testResults.getResultsId());

        return testResultsResponse;
    }

    public List<TestResultsResponse> getAllTestResults(){
        List<TestResults> testResultsList = testResultsRepo.findAll();

        return testResultsList.stream().map(this::mapToTestResultsResponse).toList();
    }

    public TestResultsResponse getTestResultsById(long id){
        return testResultsRepo.findById(id)
                .map(this::mapToTestResultsResponse)
                .orElseThrow(() -> new TestResultsNotFoundException("TestResults with ID " + id + " not found."));
    }

    public TestResultsResponse updateTestResults(TestResultsRequest testResultsRequest, long id) {
        return testResultsRepo.findById(id)
                .map(existingTestResults -> {
                    existingTestResults.setResultsDesc(testResultsRequest.getResultsDesc());

                    TestResults updatedTestResults = testResultsRepo.save(existingTestResults);
                    log.info("TestResults {} was updated", updatedTestResults.getResultsId());
                    return mapToTestResultsResponse(updatedTestResults);

                })  .orElseThrow(() -> new TestResultsNotFoundException("TestResults with ID " + id + " not found"));
    }

    public void deleteTestResults(long id){
        TestResults testResultsToDelete = testResultsRepo.findById(id)
                .orElseThrow(() -> new TestResultsNotFoundException("TestResults with ID " + id + " not found."));

        testResultsRepo.delete(testResultsToDelete);
        log.info("TestResults {} was deleted", id);
    }

    private TestResultsResponse mapToTestResultsResponse(TestResults testResults) {
        return TestResultsResponse.builder()
                .resultsId(testResults.getResultsId())
                .resultsDesc(testResults.getResultsDesc())
                .build();
    }
}
