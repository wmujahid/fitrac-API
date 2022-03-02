package com.wmujahid.fitrac.repository;

import com.wmujahid.fitrac.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    Income findByAmount(Number amount);

    Optional<Income> findIncomeById(Long id);
}