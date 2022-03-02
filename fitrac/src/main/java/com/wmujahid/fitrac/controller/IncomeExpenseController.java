package com.wmujahid.fitrac.controller;

import com.wmujahid.fitrac.model.Budget;
import com.wmujahid.fitrac.model.Expense;
import com.wmujahid.fitrac.model.Income;
import com.wmujahid.fitrac.service.BudgetService;
import com.wmujahid.fitrac.service.IncomeExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budget")
public class IncomeExpenseController {
    private final IncomeExpenseService incomeExpenseService;

    public IncomeExpenseController(IncomeExpenseService incomeExpenseService){
        this.incomeExpenseService = incomeExpenseService;
    }

    @GetMapping("/income/all")
    public ResponseEntity<List<Income>> getAllIncome() {
        List<Income> incomes = incomeExpenseService.findAllIncomes();
        return new ResponseEntity<>(incomes, HttpStatus.OK);
    }

    @GetMapping("/income/find/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable(value = "id") Long id) {
            Income income = incomeExpenseService.findIncomeById(id);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @PostMapping("/income/create")
    public ResponseEntity<Income> createIncome(@RequestBody Income income) {
        Income newIncome = incomeExpenseService.createIncome(income);
        return new ResponseEntity<>(newIncome, HttpStatus.CREATED);
    }

    @PutMapping("/income/update/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable(
            value = "id") Long id, @RequestBody Income incomeObject){
        Income updateIncome = incomeExpenseService.updateIncome(id, incomeObject);
        return new ResponseEntity<>(updateIncome, HttpStatus.OK);
    }

    @DeleteMapping("/income/delete/{id}")
    public Optional<Income> deleteIncome(@PathVariable(value = "id") Long id) {
        return incomeExpenseService.deleteIncome(id);
    }

    //--------------------------------------------------------------------//

    @GetMapping("/expense/all")
    public ResponseEntity<List<Expense>> getAllExpense() {
        List<Expense> expenses = incomeExpenseService.findAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/expense/find/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable(value = "id") Long id) {
        Expense expense = incomeExpenseService.findExpenseById(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PostMapping("/expense/create")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense newExpense = incomeExpenseService.createExpense(expense);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @PutMapping("/expense/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable(
            value = "id") Long id, @RequestBody Expense expenseObject){
        Expense updateExpense = incomeExpenseService.updateExpense(id, expenseObject);
        return new ResponseEntity<>(updateExpense, HttpStatus.OK);
    }

    @DeleteMapping("/expense/delete/{id}")
    public Optional<Expense> deleteExpense(@PathVariable(value = "id") Long id) {
        return incomeExpenseService.deleteExpense(id);
    }
}
