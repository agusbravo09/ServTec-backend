package com.example.ServTec.controller;

import com.example.ServTec.model.Client;
import com.example.ServTec.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping
    public List<Client> getAll(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id){
        return clientService.getClientById(id).orElse(null);
    }

    @GetMapping("/dni/{dni}")
    public Optional<Client> getByDni(@PathVariable String dni){
        return clientService.getClientByDni(dni);
    }

    @GetMapping("/name/{name}")
    public List<Client> getByName(@PathVariable String name){
        return clientService.searchClientsByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Client client){
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return clientService.deleteClient(id);
    }
}
