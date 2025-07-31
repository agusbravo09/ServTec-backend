package com.example.ServTec.service.impl;

import com.example.ServTec.model.Client;
import com.example.ServTec.repository.IClientRepository;
import com.example.ServTec.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    @Autowired
    private IClientRepository clientRepo;

    @Override
    @Transactional
    public void createClient(Client client) {
        clientRepo.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getClientById(Long id) {
        return clientRepo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getClientByDni(String dni) {
        return clientRepo.findByDni(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> searchClientsByName(String name) {
        return clientRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public void updateClient(Long id, Client client) {
        Client client1 = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));

        client1.setName(client.getName());
        client1.setCellphone(client.getCellphone());
        client1.setAddress(client.getAddress());
        client1.setEmail(client.getEmail());

        clientRepo.save(client1);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        Client client1 = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));

        if(!client1.getComputersList().isEmpty()){
            throw new RuntimeException("No se puede eliminar al cliente porque tiene computadoras asociadas.");
        }
        clientRepo.deleteById(client1.getId());
    }
}
