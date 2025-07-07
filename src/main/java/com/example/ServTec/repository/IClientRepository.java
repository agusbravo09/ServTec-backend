package com.example.ServTec.repository;

import com.example.ServTec.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByDni(String dni);
    List<Client> findByNameContainingIgnoreCase(String name);
}
