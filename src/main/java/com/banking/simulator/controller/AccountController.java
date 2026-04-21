package com.banking.simulator.controller;

import com.banking.simulator.entity.Account;
import com.banking.simulator.entity.Transaction;
import com.banking.simulator.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Map<String, Object> createAccount(@RequestBody Account account) {
        Long id = accountService.createAccount(account);
        return Map.of("id", id, "message", "Account created successfully");
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/deposit/{id}")
    public Account deposit(@PathVariable Long id, @RequestParam double amount) {
        return accountService.deposit(id, amount);
    }

    @PostMapping("/withdraw/{id}")
    public Account withdraw(@PathVariable Long id, @RequestParam double amount) {
        return accountService.withdraw(id, amount);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromId,
                           @RequestParam Long toId,
                           @RequestParam double amount) {

        accountService.transfer(fromId, toId, amount);
        return "Transfer successful";
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long id) {
        return accountService.getTransactions(id);
    }
    @GetMapping("/")
    public String home() {
        return "index"; // this points to index.html in templates
    }

    @GetMapping("/deposit")
    public String deposit() {
        return "deposit"; // deposit.html in templates
    }

    @GetMapping("/withdraw")
    public String withdraw() {
        return "withdraw"; // withdraw.html in templates
    }

    @GetMapping("/balance")
    public String balance() {
        return "balance"; // balance.html in templates
    }
    @GetMapping("/createAccount")
    public String createAccount() {
        return "createAccount"; // balance.html in templates
    }
    @PostMapping("/deposit")
    public String handleDeposit(@RequestParam double amount) {
        // Logic to add amount to user's balance
        System.out.println("Deposited: " + amount);
        return "redirect:/"; // go back to home page
    }
}