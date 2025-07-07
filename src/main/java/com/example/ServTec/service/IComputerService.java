package com.example.ServTec.service;

import com.example.ServTec.model.Computer;

import java.util.List;
import java.util.Optional;

public interface IComputerService {
    String createComputer(Computer computer);
    List<Computer> getAllComputers();
    Optional<Computer> getComputerById(Long id);
    List<Computer> getComputersByClientId(Long clientId);
    String updateComputer(Long id, Computer computerDetails);
    String deleteComputer(Long id);
    List<Computer> searchComputersByType(String type);
}
