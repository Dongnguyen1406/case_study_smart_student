package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Role;
import com.example.quan_ly_sinh_vien_codegym.repository.IRoleRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.RoleRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IRoleService;

import java.util.List;

public class RoleService implements IRoleService {
    private IRoleRepository iRoleRepository = new RoleRepository();
    @Override
    public List<Role> findAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public Role select(int id) {
        return null;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void add(Role role) {

    }
}
