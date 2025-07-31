package com.example.ServTec.controller;

import com.example.ServTec.dto.utils.ClientDTO;
import com.example.ServTec.model.Client;
import com.example.ServTec.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping
    public List<ClientDTO> getAll(){
        return clientService.getAllClients().stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id){
        return clientService.getClientById(id).map(ClientDTO::new).orElse(null);
    }

    @GetMapping("/dni/{dni}")
    public Optional<ClientDTO> getByDni(@PathVariable String dni){
        return clientService.getClientByDni(dni).map(ClientDTO::new);
    }

    @GetMapping("/name/{name}")
    public List<ClientDTO> getByName(@PathVariable String name){
        return clientService.searchClientsByName(name).stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Client client){
        clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Client client){
        clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}
