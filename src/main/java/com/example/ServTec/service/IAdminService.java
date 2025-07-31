package com.example.ServTec.service;

import com.example.ServTec.model.Admin;

public interface IAdminService {
    Admin getByUsername(String username);
    void createAdmin(Admin admin);
    void updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
}
