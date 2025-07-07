package com.example.ServTec.controller;

import com.example.ServTec.model.JobItem;
import com.example.ServTec.service.IJobItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
@RequiredArgsConstructor
public class JobItemController {
    @Autowired
    private IJobItemService jobService;

    @GetMapping("/budget/{budgetId}")
    public List<JobItem> getByBudget(@PathVariable Long budgetId){
        return jobService.getJobItemsByBudgetId(budgetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody JobItem item){
        return jobService.createJobItem(item);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody JobItem item){
        return jobService.updateJobItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return jobService.deleteJobItem(id);
    }
}
