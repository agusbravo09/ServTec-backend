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
