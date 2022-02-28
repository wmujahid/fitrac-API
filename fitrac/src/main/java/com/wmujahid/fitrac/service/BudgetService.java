package com.wmujahid.fitrac.service;

import com.wmujahid.fitrac.exception.InformationNotFoundException;
import com.wmujahid.fitrac.model.Budget;
import com.wmujahid.fitrac.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository){
        this.budgetRepository = budgetRepository;
    }

    public Budget addBudget(Budget budget) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        budget.setCreationDate(dtf.format(now));
        return budgetRepository.save(budget);
    }

    public List<Budget> findAllBudgets(){
        return budgetRepository.findAll();
    }

    public Budget findBudgetById(Long id) {
        return budgetRepository.findBudgetById(id)
                .orElseThrow(() -> new InformationNotFoundException("budget with id " + id + " not found"));
    }

    public Budget updateBudget(Budget budget){
        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id){
        budgetRepository.deleteBudgetById(id);
    }
}
