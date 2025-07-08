package com.example.ServTec.dto.utils;

import com.example.ServTec.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor

public class ClientDTO {
    private Long id;
    private String dni;
    private String name;
    private String cellphone;
    private String address;
    private String email;
    private String location;
    private List<ComputerDTO> computers;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.dni = client.getDni();
        this.name = client.getName();
        this.cellphone = client.getCellphone();
        this.address = client.getAddress();
        this.email = client.getEmail();
        if(client.getComputersList() != null){
            this.computers = client.getComputersList().stream()
                    .map(ComputerDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
