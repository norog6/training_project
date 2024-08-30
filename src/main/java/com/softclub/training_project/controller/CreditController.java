package com.softclub.training_project.controller;

import com.softclub.training_project.dto.CreditDTO;
import com.softclub.training_project.service.CreditService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Работа с кредитами")
@RestController
@RequestMapping("/credits")
public class CreditController {
    @Autowired
    CreditService creditService;
    @PostMapping("/{userid}/openCredit")
    public ResponseEntity<String> openCredit(@PathVariable Long userid,@Valid @RequestBody CreditDTO creditDTO) {
        creditService.openCredit(userid,creditDTO);
        return ResponseEntity.ok("Success");
    }
    @PutMapping("/{creditId}/payPerMonth")
    public ResponseEntity<String> payPerMonth(@PathVariable Long creditId, Long accountId) {
        return ResponseEntity.ok(creditService.payPerMonth(creditId,accountId));
    }
}
