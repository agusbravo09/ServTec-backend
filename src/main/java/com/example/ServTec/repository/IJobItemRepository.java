package com.example.ServTec.repository;

import com.example.ServTec.model.JobItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobItemRepository extends JpaRepository<JobItem, Long> {
    List<JobItem> findByBudgetId(Long budgetId);
}
