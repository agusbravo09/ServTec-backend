package com.example.ServTec.controller;

import com.example.ServTec.model.Computer;
import com.example.ServTec.service.IComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/computers")
@RequiredArgsConstructor
public class ComputerController {
    @Autowired
    private IComputerService computerService;

    @GetMapping
    public List<Computer> getAll(){
        return computerService.getAllComputers();
    }

    @GetMapping("/client/{clientId}")
    public List<Computer> getByClient(@PathVariable Long clientId){
        return computerService.getComputersByClientId(clientId);
    }

    @GetMapping("/{id}")
    public Optional<Computer> getById(@PathVariable Long id){
        return computerService.getComputerById(id);
    }

    @GetMapping("/type/{type}")
    public List<Computer> getByType(@PathVariable String type){
        return computerService.searchComputersByType(type);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Computer computer){
        return computerService.createComputer(computer);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Computer computer){
        return computerService.updateComputer(id, computer);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return computerService.deleteComputer(id);
    }

}
