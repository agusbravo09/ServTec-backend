package com.example.ServTec.service.impl;

import com.example.ServTec.model.*;
import com.example.ServTec.repository.*;
import com.example.ServTec.service.IBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService implements IBudgetService {
    @Autowired
    private IBudgetRepository budgetRepo;
    @Autowired
    private IClientRepository clientRepo;
    @Autowired
    private IComputerRepository computerRepo;
    @Autowired
    private IJobItemRepository jobItemRepo;
    @Autowired
    private IPartItemRepository partItemRepo;

    @Override
    @Transactional
    public String createBudget(Budget budget) {
        Client client1 = clientRepo.findById(budget.getClient().getId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Computer computer1 = computerRepo.findById(budget.getComputer().getId()).orElseThrow(() -> new RuntimeException("Computadora no encontrada"));

        budget.setClient(client1);
        budget.setComputer(computer1);
        budget.setStatus(BudgetStatus.PENDING); // Estado inicial

        Budget savedBudget = budgetRepo.save(budget);

        //guardar items si existen.
        if(budget.getJobItems() != null){
            budget.getJobItems().forEach(item -> {
                item.setBudget(savedBudget);
                jobItemRepo.save(item);
            });
        }

        if(budget.getPartItems() != null){
            budget.getPartItems().forEach(item -> {
                item.setBudget(savedBudget);
                partItemRepo.save(item);
            });
        }

        savedBudget.calculateTotal();
        budgetRepo.save(savedBudget);
        return "Presupuesto creado con exito.";
    }

    @Override
    @Transactional(readOnly = true)
    public List<Budget> getAllBudgets() {
        return budgetRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Budget> getBudgetsByClientId(Long clientId) {
        return budgetRepo.findByClientId(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Budget> getBudgetsByComputerId(Long computerId) {
        return budgetRepo.findByComputerId(computerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Budget> getBudgetsByStatus(String status) {
        return budgetRepo.findByStatus(BudgetStatus.valueOf(status.toUpperCase()));
    }

    @Override
    @Transactional
    public String updateBudget(Long id, Budget budgetDetails) {
        Budget budget1 = budgetRepo.findById(id).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con ID: " + id));

        budget1.setValidityDate(budgetDetails.getValidityDate());
        budget1.setComments(budgetDetails.getComments());
        budget1.setStatus(budgetDetails.getStatus());

        budget1.calculateTotal();

        budgetRepo.save(budget1);

        return "Presupuesto actualizado con exito";
    }

    @Override
    @Transactional
    public String deleteBudget(Long id) {
        Budget budget = budgetRepo.findById(id).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con ID: " + id));

        budgetRepo.delete(budget);
        return "Presupuesto eliminado con exito";
    }

    @Override
    @Transactional
    public String addJobItemToBudget(Long budgetId, JobItem jobItem) {
        Budget budget = budgetRepo.findById(budgetId).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con ID: " + budgetId));

        jobItem.setBudget(budget);
        jobItemRepo.save(jobItem);

        budget.calculateTotal();
        budgetRepo.save(budget);

        return "Trabajo realizado agregado con exito.";
    }

    @Override
    @Transactional
    public String addPartItemToBudget(Long budgetId, PartItem partItem) {
        Budget budget = budgetRepo.findById(budgetId).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con ID: " + budgetId));

        partItem.setBudget(budget);
        partItemRepo.save(partItem);

        budget.calculateTotal();
        budgetRepo.save(budget);

        return "Parte agregada con exito.";
    }
}
