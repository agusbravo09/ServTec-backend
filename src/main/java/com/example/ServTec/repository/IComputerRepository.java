package com.example.ServTec.repository;

import com.example.ServTec.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer> findByClientId(Long id);
    List<Computer> findByTypeContainingIgnoreCase(String type);
}
