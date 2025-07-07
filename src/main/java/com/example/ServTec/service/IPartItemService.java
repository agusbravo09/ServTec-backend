package com.example.ServTec.service;

import com.example.ServTec.model.PartItem;

import java.util.List;

public interface IPartItemService {
    String createPartItem(PartItem partItem);
    List<PartItem> getPartItemsByBudgetId(Long budgetId);
    String updatePartItem(Long id, PartItem partItemDetails);
    String deletePartItem(Long id);
}
