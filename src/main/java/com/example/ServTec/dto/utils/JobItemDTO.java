package com.example.ServTec.dto.utils;


import com.example.ServTec.model.JobItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class JobItemDTO {
    private Long id;
    private String description;
    private Double hours;
    private Double hourPrice;
    private Double amount;

    public JobItemDTO(JobItem job) {
        this.id = job.getId();
        this.description = job.getDescription();
        this.hours = job.getHours();
        this.hourPrice = job.getHourPrice();
        this.amount = job.getAmount();
    }
}
