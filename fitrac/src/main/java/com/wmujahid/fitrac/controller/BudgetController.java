package com.wmujahid.fitrac.controller;

import com.wmujahid.fitrac.model.Budget;
import com.wmujahid.fitrac.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService){
        this.budgetService = budgetService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = budgetService.findAllBudgets();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable(value = "id") Long id) {
            Budget budget = budgetService.findBudgetById(id);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget newBudget = budgetService.createBudget(budget);
        return new ResponseEntity<>(newBudget, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Budget> updateBudget(@RequestBody Budget budget){
        Budget updateBudget = budgetService.updateBudget(budget);
        return new ResponseEntity<>(updateBudget, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable(value = "id") Long id) {
        budgetService.findBudgetById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
