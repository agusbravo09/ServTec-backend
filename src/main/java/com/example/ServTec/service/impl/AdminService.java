package com.example.ServTec.service.impl;

import com.example.ServTec.model.Admin;
import com.example.ServTec.repository.IAdminRepository;
import com.example.ServTec.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IAdminRepository adminRepo;

    @Override
    public Admin getByUsername(String username) {
        return adminRepo.findByUsername(username);
    }

    @Override
    public void createAdmin(Admin admin) {
        adminRepo.save(admin);
    }

    @Override
    public void updateAdmin(Long id, Admin admin) {
        Admin admin1 = adminRepo.findById(id).orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));

        admin1.setUsername(admin.getUsername());
        admin1.setPassword(admin.getPassword());

        adminRepo.save(admin1);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }
}
