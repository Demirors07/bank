package com.example.bank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.bank.dto.LoanDTO;
import com.example.bank.model.Loan;
import com.example.bank.model.Account;
import com.example.bank.model.Customer;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.repository.LoanRepository;


import jakarta.transaction.Transactional;

@Service
public class LoanService {
    private final LoanRepository loanRepo;
    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;

    public LoanService(LoanRepository loanRepo, AccountRepository accountRepo, CustomerRepository customerRepo) {
        this.loanRepo = loanRepo;
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
    }

@Transactional
    public LoanDTO createLoan(LoanDTO request) {
        Account account = accountRepo.findById(request.getAccountId())
        .orElseThrow(() -> new RuntimeException("Account not found"));

        Customer customer = customerRepo.findById(request.getCustomerId())
        .orElseThrow(() -> new RuntimeException("Customer not found"));

        Loan loan = new Loan();
        loan.setDate(request.getDate());
        loan.setAmount(request.getAmount());
        loan.setAccount(account);
        loan.setCustomer(customer);

        Loan saved = loanRepo.save(loan);

        return mapToDTO(saved);
    }


    public List<LoanDTO> getAllLoans() {
        return loanRepo.findAll()
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
        //return loans;
    }

    public LoanDTO getOneLoan(Long id) {
        Loan loan = loanRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Loan not found"));

        return mapToDTO(loan);
    }


@Transactional
    public LoanDTO update(Long id, LoanDTO request) {

    Loan loan = loanRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Loan not found"));
    Account account = accountRepo.findById(request.getAccountId())
            .orElseThrow(() -> new RuntimeException("Account not found"));
    Customer customer = customerRepo.findById(request.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    loan.setDate(request.getDate());
    loan.setAmount(request.getAmount()); 
    loan.setAccount(account);
    loan.setCustomer(customer);

    return mapToDTO(loan);
}


    public void deleteLoan(Long id) {
        if(!loanRepo.existsById(id)) {
            throw new RuntimeException("Loan not found");
        }

        loanRepo.deleteById(id);
    }



    private LoanDTO mapToDTO(Loan loan) {

        return new LoanDTO(
            loan.getId(),
            loan.getDate(),
            loan.getAmount(),
            loan.getAccount().getId(),
            loan.getCustomer().getId()
        );
    }
}