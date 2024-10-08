package com.softclub.training_project.controller;

import com.softclub.training_project.dto.AccountDTO;
import com.softclub.training_project.entity.Account;
import com.softclub.training_project.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name="Работа с аккаунтами")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/open")
    public ResponseEntity<Account> openAccount(@RequestParam Long userId, @RequestBody AccountDTO accountDto) {
        return ResponseEntity.ok(accountService.openAccount(userId, accountDto));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount) {
        accountService.Transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok("Successful");
    }


    @PutMapping("/{accountId}/addmoney")
    public ResponseEntity<String> addMoney(@PathVariable Long accountId, @RequestParam double amount) {
        accountService.addMoneyToAccount(accountId, amount);
        return ResponseEntity.ok("Successful");
    }
}
