package com.banking.simulator.service;

import com.banking.simulator.entity.Transaction;
import com.banking.simulator.repository.TransactionRepository;
import java.time.LocalDateTime;
import com.banking.simulator.entity.Account;
import com.banking.simulator.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.simulator.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private EmailService emailService;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Long createAccount(Account account) {
        Account saved = accountRepository.save(account);
        String body = "Hello " + saved.getName() + ",\n\n" +
                "Your account has been successfully created.\n\n" +
                "Account Details:\n" +
                "----------------------------------\n" +
                "Account ID : " + saved.getId() + "\n" +
                "Name       : " + saved.getName() + "\n" +
                "Email      : " + saved.getEmail() + "\n" +
                "Balance    : ₹" + saved.getBalance() + "\n" +
                "----------------------------------\n\n" +
                "Thank you for banking with us!";

//        emailService.sendEmail(saved.getEmail(), "Account Created", body);
        try {
            emailService.sendEmail(
                    saved.getEmail(),
                    "Account Created",
                    body
            );
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
        return saved.getId();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Account deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);

        Transaction txn = new Transaction();
        txn.setType("DEPOSIT");
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());
        txn.setToAccountId(account.getId());

        transactionRepository.save(txn);

        return account;
    }
    public Account withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow();

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);

        Transaction txn = new Transaction();
        txn.setType("WITHDRAW");
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());
        txn.setFromAccountId(account.getId());

        transactionRepository.save(txn);

        return account;
    }
    public void transfer(Long fromId, Long toId, double amount) {

        Account fromAccount = accountRepository.findById(fromId).orElseThrow();
        Account toAccount = accountRepository.findById(toId).orElseThrow();

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction txn = new Transaction();
        txn.setType("TRANSFER");
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());
        txn.setFromAccountId(fromAccount.getId());
        txn.setToAccountId(toAccount.getId());

        transactionRepository.save(txn);
    }
    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository
                .findByFromAccountIdOrToAccountId(accountId, accountId);
    }

}