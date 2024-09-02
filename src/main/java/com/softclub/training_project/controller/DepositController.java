package com.softclub.training_project.controller;

import com.softclub.training_project.dto.CreditDTO;
import com.softclub.training_project.dto.DepositDTO;
import com.softclub.training_project.service.DepositService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Работа с депозитами")
@RestController
@RequestMapping("/deposits")
public class DepositController {
    @Autowired
    private DepositService depositService;

    @PostMapping("/{userid}/openDeposit")
    public ResponseEntity<String> openDeposit(@PathVariable Long userid,@RequestParam Long accountId, @Valid @RequestBody DepositDTO depositDTO) {
        depositService.openDeposit(userid,accountId,depositDTO);
        return ResponseEntity.ok("Success");
    }
    @PutMapping("/{depositId}/payTo")
    public ResponseEntity<String> payToDeposit(@PathVariable Long depositId,@RequestParam Long accountId, double amount) {
        depositService.payToDeposit(depositId,accountId,amount);
        return ResponseEntity.ok("Success");
    }
    @DeleteMapping("/{depositId}/getDeposit")
    public ResponseEntity<String> deleteDeposit(@PathVariable Long depositId,@RequestParam Long accountId) {
        depositService.getMoneyFromDeposit(depositId,accountId);
        return ResponseEntity.ok("Success");
    }

}
