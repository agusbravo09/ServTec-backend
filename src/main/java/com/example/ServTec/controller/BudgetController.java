package com.example.ServTec.controller;

import com.example.ServTec.model.Budget;
import com.example.ServTec.model.BudgetStatus;
import com.example.ServTec.model.JobItem;
import com.example.ServTec.model.PartItem;
import com.example.ServTec.service.IBudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    @Autowired
    private IBudgetService budgetService;

    @GetMapping
    public List<Budget> getAll(){
        return budgetService.getAllBudgets();
    }

    @GetMapping("/client/{clientId}")
    public List<Budget> getByClient(@PathVariable Long clientId){
        return budgetService.getBudgetsByClientId(clientId);
    }

    @GetMapping("/{id}")
    public Optional<Budget> getById(@PathVariable Long id){
        return budgetService.getBudgetById(id);
    }

    @GetMapping("/computer/{computerId}")
    public List<Budget> getByComputerId(@PathVariable Long computerId){
        return budgetService.getBudgetsByComputerId(computerId);
    }

    @GetMapping("/status/{status}")
    public List<Budget> getByStatus(@PathVariable String status){
        return budgetService.getBudgetsByStatus(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Budget budget){
        return budgetService.createBudget(budget);
    }

    @PostMapping("/{id}/add-job")
    public String addJob(@PathVariable Long id, @RequestBody JobItem item){
        return budgetService.addJobItemToBudget(id, item);
    }

    @PostMapping("/{id}/add-part")
    public String addPart(@PathVariable Long id, @RequestBody PartItem item){
        return budgetService.addPartItemToBudget(id, item);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Budget budget){
        return budgetService.updateBudget(id, budget);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return budgetService.deleteBudget(id);
    }
}
