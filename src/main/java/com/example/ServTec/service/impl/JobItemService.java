package com.example.ServTec.service.impl;

import com.example.ServTec.model.Budget;
import com.example.ServTec.model.JobItem;
import com.example.ServTec.repository.IBudgetRepository;
import com.example.ServTec.repository.IJobItemRepository;
import com.example.ServTec.service.IJobItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobItemService implements IJobItemService {
    @Autowired
    private IJobItemRepository jobRepo;
    @Autowired
    private IBudgetRepository budgetRepo;

    @Override
    @Transactional
    public String createJobItem(JobItem jobItem) {
        Budget budget = budgetRepo.findById(jobItem.getBudget().getId()).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado."));

        jobItem.setBudget(budget);
        jobRepo.save(jobItem);

        budget.calculateTotal();
        budgetRepo.save(budget);

        return "Trabajo realizado agregado con exito.";
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobItem> getJobItemsByBudgetId(Long budgetId) {
        return jobRepo.findByBudgetId(budgetId);
    }

    @Override
    @Transactional
    public String updateJobItem(Long id, JobItem jobItemDetails) {
        JobItem job = jobRepo.findById(id).orElseThrow(() -> new RuntimeException("Trabajo no encontrado con ID: " + id));

        job.setDescription(jobItemDetails.getDescription());
        job.setHours(jobItemDetails.getHours());
        job.setHourPrice(jobItemDetails.getHourPrice());

        jobRepo.save(job);

        //actualizar presupuesto
        Budget budget = jobItemDetails.getBudget();

        budget.calculateTotal();
        budgetRepo.save(budget);
        return "Trabajo actualizado con exito.";
    }

    @Override
    public String deleteJobItem(Long id) {
        JobItem job = jobRepo.findById(id).orElseThrow(() -> new RuntimeException("Trabajo no encontrado con ID: " + id));

        Budget budget = job.getBudget();

        jobRepo.delete(job);

        budget.calculateTotal();
        budgetRepo.save(budget);
        return "Trabajo eliminado con exito.";
    }
}
