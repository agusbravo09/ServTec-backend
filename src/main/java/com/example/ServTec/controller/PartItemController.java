package com.example.ServTec.controller;

import com.example.ServTec.dto.utils.PartItemDTO;
import com.example.ServTec.model.PartItem;
import com.example.ServTec.service.IPartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parts")
@RequiredArgsConstructor
public class PartItemController {
    @Autowired
    private IPartItemService partService;

    @GetMapping("/budget/{budgetId}")
    public List<PartItemDTO> getByBudget(@PathVariable Long budgetId){
        return partService.getPartItemsByBudgetId(budgetId).stream()
                .map(PartItemDTO::new)
                .collect(Collectors.toList());
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
