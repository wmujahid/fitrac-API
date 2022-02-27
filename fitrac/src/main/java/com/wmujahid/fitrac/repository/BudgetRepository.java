package com.wmujahid.fitrac.repository;

import com.wmujahid.fitrac.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
