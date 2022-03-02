package com.wmujahid.fitrac.repository;

import com.wmujahid.fitrac.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Expense findByAmount(Number amount);

    Optional<Expense> findExpenseById(Long id);
}