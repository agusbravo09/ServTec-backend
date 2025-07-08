package com.example.ServTec.dto.utils;

import com.example.ServTec.model.Computer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ComputerDTO {
    private Long id;
    private String type;
    private String processor;
    private String motherboard;
    private String ram;
    private String storage;
    private String powerSupply;
    private String graphics;

    public ComputerDTO(Computer computer) {
        this.id = computer.getId();
        this.type = computer.getType();
        this.processor = computer.getProcessor();
        this.motherboard = computer.getMotherboard();
        this.ram = computer.getRam();
        this.storage = computer.getStorage();
        this.powerSupply = computer.getPowerSupply();
        this.graphics = computer.getGraphics();
    }
}
