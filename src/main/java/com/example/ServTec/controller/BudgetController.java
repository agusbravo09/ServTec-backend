package com.example.ServTec.controller;

import com.example.ServTec.dto.utils.BudgetDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    @Autowired
    private IBudgetService budgetService;

    @GetMapping
    public List<BudgetDTO> getAll(){
        return budgetService.getAllBudgets().stream()
                .map(BudgetDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/client/{clientId}")
    public List<BudgetDTO> getByClient(@PathVariable Long clientId){
        return budgetService.getBudgetsByClientId(clientId).stream()
                .map(BudgetDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<BudgetDTO> getById(@PathVariable Long id){
        return budgetService.getBudgetById(id).map(BudgetDTO::new);
    }

    @GetMapping("/computer/{computerId}")
    public List<BudgetDTO> getByComputerId(@PathVariable Long computerId){
        return budgetService.getBudgetsByComputerId(computerId).stream()
                .map(BudgetDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/status/{status}")
    public List<BudgetDTO> getByStatus(@PathVariable String status){
        return budgetService.getBudgetsByStatus(status).stream()
                .map(BudgetDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Budget budget){
        budgetService.createBudget(budget);
    }

    @PostMapping("/{id}/add-job")
    public void addJob(@PathVariable Long id, @RequestBody JobItem item){
        budgetService.addJobItemToBudget(id, item);
    }

    @PostMapping("/{id}/add-part")
    public void addPart(@PathVariable Long id, @RequestBody PartItem item){
        budgetService.addPartItemToBudget(id, item);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Budget budget){
        budgetService.updateBudget(id, budget);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        budgetService.deleteBudget(id);
    }
}
