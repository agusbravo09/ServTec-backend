package com.example.ServTec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor

@Entity
@Table(name = "part_items")
public class PartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @PrePersist
    @PreUpdate
    public void calculateAmount(){
        this.amount = quantity * unitPrice;
    }
}
