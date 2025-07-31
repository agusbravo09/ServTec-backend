package com.example.ServTec.service;

import com.example.ServTec.model.JobItem;

import java.util.List;

public interface IJobItemService {
    void createJobItem(JobItem jobItem);
    List<JobItem> getJobItemsByBudgetId(Long budgetId);
    void updateJobItem(Long id, JobItem jobItemDetails);
    void deleteJobItem(Long id);
}
