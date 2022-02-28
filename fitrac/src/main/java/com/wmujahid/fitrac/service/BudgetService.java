package com.wmujahid.fitrac.service;

import com.wmujahid.fitrac.exception.InformationExistsException;
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

    public Budget createBudget(Budget budgetObject) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        budgetObject.setCreationDate(dtf.format(now));
        Budget budget = budgetRepository.findByName(budgetObject.getName());
        if(budget != null){
            throw new InformationExistsException("budget with name " + budget.getName() + " already exists");
        } else {
            return budgetRepository.save(budgetObject);
        }
    }

    public List<Budget> findAllBudgets(){
        return budgetRepository.findAll();
    }

    public Budget findBudgetById(Long id) {
        return budgetRepository.findBudgetById(id)
                .orElseThrow(() -> new InformationNotFoundException("budget with id " + id + " not found"));
    }

    public Budget updateBudget(Long id, Budget budgetObject){
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent()) {
            if (budgetObject.getName().equals(budget.get().getName())) {
                throw new InformationExistsException("Budget " + budget.get().getName() + " is already exists");
            } else {
                Budget updateBudget = budgetRepository.findById(id).get();
                updateBudget.setName(budgetObject.getName());
                updateBudget.setDescription(budgetObject.getDescription());
                return budgetRepository.save(updateBudget);
            }
        } else {
            throw new InformationNotFoundException("Budget with id " + id + " not found");
        }
    }

//    public Budget deleteBudget(Long id, Budget budgetObject){
//        Optional<Budget> budget = budgetRepository.findById(id);
//        if (budget.isPresent()) {
//            budget.re
//            return budget;
//        } else {
//            throw new InformationNotFoundException("category with id " + id + " not found");
//        }
//    }

    public Optional<Budget> deleteBudget(Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent()) {
            budgetRepository.deleteById(id);
            return budget;
        } else {
            throw new InformationNotFoundException("category with id " + id + " not found");
        }
    }
}
