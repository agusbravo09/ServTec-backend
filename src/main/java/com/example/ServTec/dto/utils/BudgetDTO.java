package com.example.ServTec.dto.utils;

import com.example.ServTec.model.Budget;
import com.example.ServTec.model.BudgetStatus;
import com.example.ServTec.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor

public class BudgetDTO {
    private Long id;
    private Date emissionDate;
    private Date validityDate;
    private String budgetNumber;
    private String comments;
    private Double totalAmount;
    private BudgetStatus status;

    private Long clientId;
    private Long computerId;
    private List<JobItemDTO> jobItems;
    private List<PartItemDTO> partItems;

    public BudgetDTO(Budget budget){
        this.id = budget.getId();
        this.emissionDate = budget.getEmissionDate();
        this.validityDate = budget.getValidityDate();
        this.budgetNumber = budget.getBudgetNumber();
        this.comments = budget.getComments();
        this.totalAmount = budget.getTotalAmount();
        this.status = budget.getStatus();

        this.clientId = budget.getClient() != null ? budget.getClient().getId() : null;
        this.computerId = budget.getComputer() != null ? budget.getComputer().getId() : null;

        if(budget.getPartItems() != null){
            this.partItems = budget.getPartItems().stream()
                    .map(PartItemDTO::new)
                    .collect(Collectors.toList());
        }

        if(budget.getJobItems() != null){
            this.jobItems = budget.getJobItems().stream()
                    .map(JobItemDTO::new)
                    .collect(Collectors.toList());
        }
    }

}
