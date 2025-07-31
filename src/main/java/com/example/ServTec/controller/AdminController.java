package com.example.ServTec.controller;

import com.example.ServTec.model.Admin;
import com.example.ServTec.service.IAdminService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @GetMapping("/{username}")
    public ResponseEntity<Admin> getByUsername(@PathVariable String username){
        Admin admin1 = adminService.getByUsername(username);
        if(admin1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin1);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Admin admin){
        adminService.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        adminService.deleteAdmin(id);
    }

    @PostMapping("/createAdmin")
    public void create(@RequestBody Admin admin){
        adminService.createAdmin(admin);
    }

}
