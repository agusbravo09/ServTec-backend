package com.example.ServTec.service;

import com.example.ServTec.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    void createClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
    Optional<Client> getClientByDni(String dni);
    List<Client> searchClientsByName(String name);
    void updateClient(Long id, Client client);
    void deleteClient(Long id);
}
