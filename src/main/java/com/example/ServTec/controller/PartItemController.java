package com.example.ServTec.controller;

import com.example.ServTec.model.PartItem;
import com.example.ServTec.service.IPartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
@RequiredArgsConstructor
public class PartItemController {
    @Autowired
    private IPartItemService partService;

    @GetMapping("/budget/{budgetId}")
    public List<PartItem> getByBudget(@PathVariable Long budgetId){
        return partService.getPartItemsByBudgetId(budgetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PartItem item){
        return partService.createPartItem(item);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody PartItem item){
        return partService.updatePartItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return partService.deletePartItem(id);
    }

}
