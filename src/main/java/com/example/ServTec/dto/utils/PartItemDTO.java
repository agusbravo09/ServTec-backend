package com.example.ServTec.dto.utils;

import com.example.ServTec.model.PartItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PartItemDTO {
    private Long id;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double amount;

    public PartItemDTO(PartItem item) {
        this.id = item.getId();
        this.description = item.getDescription();
        this.quantity = item.getQuantity();
        this.unitPrice = item.getUnitPrice();
        this.amount = item.getAmount();
    }
}
