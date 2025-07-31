package com.example.ServTec.service;

import com.example.ServTec.model.PartItem;

import java.util.List;

public interface IPartItemService {
    void createPartItem(PartItem partItem);
    List<PartItem> getPartItemsByBudgetId(Long budgetId);
    void updatePartItem(Long id, PartItem partItemDetails);
    void deletePartItem(Long id);
}
