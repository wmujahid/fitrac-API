package com.wmujahid.fitrac.repository;

import com.wmujahid.fitrac.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findBudgetById(Long id); // returns an 'Optional' instead of 'Budget' because there may be a situation where we don't have the specific id

    Budget findByName(String name);

//    void deleteBudgetbyId(Long id);

}
