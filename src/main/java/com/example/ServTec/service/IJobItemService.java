package com.example.ServTec.service;

import com.example.ServTec.model.JobItem;

import java.util.List;

public interface IJobItemService {
    String createJobItem(JobItem jobItem);
    List<JobItem> getJobItemsByBudgetId(Long budgetId);
    String updateJobItem(Long id, JobItem jobItemDetails);
    String deleteJobItem(Long id);
}
