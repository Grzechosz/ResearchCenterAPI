package com.denert.app.rest.Controller;


import com.denert.app.rest.Models.Address;
import com.denert.app.rest.Models.Agreement;
import com.denert.app.rest.Models.TestResults;
import com.denert.app.rest.Repo.AgreementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgreementController {

    @Autowired
    private AgreementRepo agreementRepo;

    @GetMapping(value = "/agreements")
    public List<Agreement> getAgreements() {
        return agreementRepo.findAll();
    }

    @GetMapping(value = "/agreements/{id}")
    public Agreement getAgreement(@PathVariable long id) {
        return agreementRepo.findById(id).get();
    }

    @PostMapping(value = "/agreements")
    public ResponseEntity<Agreement> saveAgreement(@RequestBody Agreement agreement) {
        agreementRepo.save(agreement);
        return  ResponseEntity.ok(agreement);
    }

    @PutMapping(value = "/agreements/{id}")
    public ResponseEntity<Agreement> updateAgreement(@PathVariable long id, @RequestBody Agreement agreement) {
        Agreement updateAgreement = agreementRepo.findById(id).get();

        updateAgreement.setProject(agreement.getProject());
        updateAgreement.setUser(agreement.getUser());

        agreementRepo.save(updateAgreement);
        return ResponseEntity.ok(updateAgreement);
    }

    @DeleteMapping(value = "/agreements/{id}")
    public String deleteAgreement(@PathVariable long id) {
        Agreement deleteAgreement = agreementRepo.findById(id).get();
        agreementRepo.delete(deleteAgreement);
        return "Deleted agreement with id: "+id;
    }
}
