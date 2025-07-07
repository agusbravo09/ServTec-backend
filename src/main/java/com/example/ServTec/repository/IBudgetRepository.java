package com.example.ServTec.repository;

import com.example.ServTec.model.Budget;
import com.example.ServTec.model.BudgetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByClientId(Long clientId);
    List<Budget> findByComputerId(Long computerId);
    List<Budget> findByStatus(BudgetStatus status);
}
