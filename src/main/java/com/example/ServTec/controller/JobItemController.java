package com.example.ServTec.controller;

import com.example.ServTec.dto.utils.JobItemDTO;
import com.example.ServTec.model.JobItem;
import com.example.ServTec.service.IJobItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/jobs")
@RequiredArgsConstructor
public class JobItemController {
    @Autowired
    private IJobItemService jobService;

    @GetMapping("/budget/{budgetId}")
    public List<JobItemDTO> getByBudget(@PathVariable Long budgetId){
        return jobService.getJobItemsByBudgetId(budgetId).stream()
                .map(JobItemDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody JobItem item){
        jobService.createJobItem(item);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody JobItem item){
        jobService.updateJobItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        jobService.deleteJobItem(id);
    }
}
