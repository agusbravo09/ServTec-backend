package com.example.ServTec.service;

import com.example.ServTec.model.Budget;
import com.example.ServTec.model.JobItem;
import com.example.ServTec.model.PartItem;

import java.util.List;
import java.util.Optional;

public interface IBudgetService {
    String createBudget(Budget budget);
    List<Budget> getAllBudgets();
    Optional<Budget> getBudgetById(Long id);
    List<Budget> getBudgetsByClientId(Long clientId);
    List<Budget> getBudgetsByComputerId(Long computerId);
    List<Budget> getBudgetsByStatus(String status);
    String updateBudget(Long id, Budget budgetDetails);
    String deleteBudget(Long id);
    String addJobItemToBudget(Long budgetId, JobItem jobItem);
    String addPartItemToBudget(Long budgetId, PartItem partItem);
}
