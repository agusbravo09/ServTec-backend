package com.example.ServTec.repository;

import com.example.ServTec.model.PartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPartItemRepository extends JpaRepository<PartItem, Long> {
    List<PartItem> findByBudgetId(Long budgetId);
}
