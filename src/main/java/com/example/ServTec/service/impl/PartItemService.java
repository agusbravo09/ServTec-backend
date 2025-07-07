package com.example.ServTec.service.impl;

import com.example.ServTec.model.Budget;
import com.example.ServTec.model.PartItem;
import com.example.ServTec.repository.IBudgetRepository;
import com.example.ServTec.repository.IPartItemRepository;
import com.example.ServTec.service.IPartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartItemService implements IPartItemService {
    @Autowired
    private IPartItemRepository partRepo;
    @Autowired
    private IBudgetRepository budgetRepo;

    @Override
    @Transactional
    public String createPartItem(PartItem partItem) {
        Budget budget = budgetRepo.findById(partItem.getBudget().getId()).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado"));

        partItem.setBudget(budget);
        partRepo.save(partItem);

        budget.calculateTotal();

        budgetRepo.save(budget);

        return "Parte agregada con exito.";
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartItem> getPartItemsByBudgetId(Long budgetId) {
        return partRepo.findByBudgetId(budgetId);
    }

    @Override
    @Transactional
    public String updatePartItem(Long id, PartItem partItemDetails) {
        PartItem part = partRepo.findById(id).orElseThrow(() -> new RuntimeException("Parte no encontrada."));

        part.setDescription(partItemDetails.getDescription());
        part.setQuantity(partItemDetails.getQuantity());
        part.setUnitPrice(partItemDetails.getUnitPrice());

        partRepo.save(part);

        //actualizar el total del presupuesto
        Budget budget = partItemDetails.getBudget();
        budget.calculateTotal();
        budgetRepo.save(budget);

        return "Parte actualizada con exito.";
    }

    @Override
    public String deletePartItem(Long id) {
        PartItem part = partRepo.findById(id).orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));

        Budget budget = part.getBudget();
        partRepo.delete(part);

        budget.calculateTotal();
        budgetRepo.save(budget);

        return "Parte eliminada con exito.";
    }
}
