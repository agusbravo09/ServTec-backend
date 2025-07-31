package com.example.ServTec.service.impl;

import com.example.ServTec.model.Client;
import com.example.ServTec.model.Computer;
import com.example.ServTec.repository.IClientRepository;
import com.example.ServTec.repository.IComputerRepository;
import com.example.ServTec.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService implements IComputerService {

    @Autowired
    private IComputerRepository computerRepo;
    @Autowired
    private IClientRepository clientRepo;

    @Override
    @Transactional
    public void createComputer(Computer computer) {
        Client client1 = clientRepo.findById(computer.getClient().getId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado."));
        computer.setClient(client1);

        computerRepo.save(computer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Computer> getAllComputers() {
        return computerRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Computer> getComputerById(Long id) {
        return computerRepo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Computer> getComputersByClientId(Long clientId) {
        return computerRepo.findByClientId(clientId);
    }

    @Override
    @Transactional
    public void updateComputer(Long id, Computer computerDetails) {
        Computer computer1 = computerRepo.findById(id).orElseThrow(() -> new RuntimeException("Computadora no encontrada con el ID: " + id));

        computer1.setType(computerDetails.getType());
        computer1.setProcessor(computerDetails.getProcessor());
        computer1.setMotherboard(computerDetails.getMotherboard());
        computer1.setRam(computerDetails.getRam());
        computer1.setStorage(computerDetails.getStorage());
        computer1.setPowerSupply(computerDetails.getPowerSupply());
        computer1.setGraphics(computerDetails.getGraphics());

        computerRepo.save(computer1);

    }

    @Override
    @Transactional
    public void deleteComputer(Long id) {
        Computer computer = computerRepo.findById(id).orElseThrow(() -> new RuntimeException("Computadora no encontrada con el ID: " + id));

        if(!computer.getBudgetList().isEmpty()){
            throw new RuntimeException("No se puede eliminar la computadora porque tiene presupuestos asociados.");
        }

        computerRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Computer> searchComputersByType(String type) {
        return computerRepo.findByTypeContainingIgnoreCase(type);
    }
}
