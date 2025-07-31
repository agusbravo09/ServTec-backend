package com.example.ServTec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emission_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date emissionDate;

    @Column(name = "validity_date")
    @Temporal(TemporalType.DATE)
    private Date validityDate;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private BudgetStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<JobItem> jobItems;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<PartItem> partItems;

    //metodo para calcular el monto total.
    public void calculateTotal(){
        Double jobTotal = (jobItems != null) ?
                jobItems.stream().mapToDouble(JobItem::getAmount).sum() : 0.0;
        Double partTotal = (partItems != null) ?
                partItems.stream().mapToDouble(PartItem::getAmount).sum() : 0.0;
        this.totalAmount = jobTotal + partTotal;
    }
}
