package com.softclub.training_project.controller;

import com.softclub.training_project.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="админ панель")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AccountService accountService;


    @PostMapping("/{accountId}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable Long accountId) {
        accountService.blockAccount(accountId);
        return ResponseEntity.ok().build();
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{accountId}/unblock")
    public ResponseEntity<Void> unblockAccount(@PathVariable Long accountId) {
        accountService.unblockAccount(accountId);
        return ResponseEntity.ok().build();
    }
}
