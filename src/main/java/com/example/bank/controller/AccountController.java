package com.example.bank.controller;

import com.example.bank.service.AccountService;
import com.example.bank.dto.AccountDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public AccountDTO create(@RequestBody AccountDTO request) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public List<AccountDTO> getAll() { 
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDTO getOne(@PathVariable Long id) {
        return accountService.getOne(id);
    }

    @PutMapping("/{id}")
    public AccountDTO update(@PathVariable Long id, @RequestBody AccountDTO request) {
        return accountService.updateAccount(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}