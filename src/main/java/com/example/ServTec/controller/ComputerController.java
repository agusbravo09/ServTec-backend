package com.example.ServTec.controller;

import com.example.ServTec.dto.utils.ComputerDTO;
import com.example.ServTec.model.Computer;
import com.example.ServTec.service.IComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/computers")
@RequiredArgsConstructor
public class ComputerController {
    @Autowired
    private IComputerService computerService;

    @GetMapping
    public List<ComputerDTO> getAll(){
        return computerService.getAllComputers().stream()
                .map(ComputerDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/client/{clientId}")
    public List<ComputerDTO> getByClient(@PathVariable Long clientId){
        return computerService.getComputersByClientId(clientId).stream()
                .map(ComputerDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<ComputerDTO> getById(@PathVariable Long id){
        return computerService.getComputerById(id).map(ComputerDTO::new);
    }

    @GetMapping("/type/{type}")
    public List<ComputerDTO> getByType(@PathVariable String type){
        return computerService.searchComputersByType(type).stream()
                .map(ComputerDTO::new)
                .collect(Collectors.toList());
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
