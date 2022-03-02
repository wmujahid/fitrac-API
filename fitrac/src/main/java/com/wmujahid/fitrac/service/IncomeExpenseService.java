package com.wmujahid.fitrac.service;

import com.wmujahid.fitrac.exception.InformationExistsException;
import com.wmujahid.fitrac.exception.InformationNotFoundException;
import com.wmujahid.fitrac.model.Expense;
import com.wmujahid.fitrac.model.Income;
import com.wmujahid.fitrac.repository.ExpenseRepository;
import com.wmujahid.fitrac.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class IncomeExpenseService {
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public IncomeExpenseService(ExpenseRepository expenseRepository, IncomeRepository incomeRepository){
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public Income createIncome(Income incomeObject) {
        Income income = incomeRepository.findByAmount(incomeObject.getAmount());
        if(income != null){
            throw new InformationExistsException("income with amount " + income.getAmount() + " already exists");
        } else {
            return incomeRepository.save(incomeObject);
        }
    }

    public List<Income> findAllIncomes(){
        return incomeRepository.findAll();
    }

    public Income findIncomeById(Long id) {
        return incomeRepository.findIncomeById(id)
                .orElseThrow(() -> new InformationNotFoundException("income with id " + id + " not found"));
    }

    public Income updateIncome(Long id, Income incomeObject){
        Optional<Income> income = incomeRepository.findById(id);
        if (income.isPresent()) {
            if (incomeObject.getAmount().equals(income.get().getAmount())) {
                throw new InformationExistsException("Income " + income.get().getAmount() + " is already exists");
            } else {
                Income updateIncome = incomeRepository.findById(id).get();
                updateIncome.setAmount(incomeObject.getAmount());
                updateIncome.setDescription(incomeObject.getDescription());
                return incomeRepository.save(updateIncome);
            }
        } else {
            throw new InformationNotFoundException("Income with id " + id + " not found");
        }
    }

    public Optional<Income> deleteIncome(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        if (income.isPresent()) {
            incomeRepository.deleteById(id);
            return income;
        } else {
            throw new InformationNotFoundException("Income with id " + id + " not found");
        }
    }

    //--------------------------------------------------------------//

    public Expense createExpense(Expense expenseObject) {
        Expense expense = expenseRepository.findByAmount(expenseObject.getAmount());
        if(expense != null){
            throw new InformationExistsException("expense with amount " + expense.getAmount() + " already exists");
        } else {
            return expenseRepository.save(expenseObject);
        }
    }

    public List<Expense> findAllExpenses(){
        return expenseRepository.findAll();
    }

    public Expense findExpenseById(Long id) {
        return expenseRepository.findExpenseById(id)
                .orElseThrow(() -> new InformationNotFoundException("expense with id " + id + " not found"));
    }

    public Expense updateExpense(Long id, Expense expenseObject){
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            if (expenseObject.getAmount().equals(expense.get().getAmount())) {
                throw new InformationExistsException("Expense " + expense.get().getAmount() + " is already exists");
            } else {
                Expense updateExpense = expenseRepository.findById(id).get();
                updateExpense.setAmount(expenseObject.getAmount());
                updateExpense.setDescription(expenseObject.getDescription());
                return expenseRepository.save(updateExpense);
            }
        } else {
            throw new InformationNotFoundException("Expense with id " + id + " not found");
        }
    }

    public Optional<Expense> deleteExpense(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            expenseRepository.deleteById(id);
            return expense;
        } else {
            throw new InformationNotFoundException("Expense with id " + id + " not found");
        }
    }
}
