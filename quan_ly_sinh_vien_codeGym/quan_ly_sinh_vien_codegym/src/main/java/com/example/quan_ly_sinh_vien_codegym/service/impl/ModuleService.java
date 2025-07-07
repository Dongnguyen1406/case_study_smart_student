package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Module;
import com.example.quan_ly_sinh_vien_codegym.repository.ICourseRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.IModuleRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.CourseRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.ModuleRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IModuleService;

import java.util.List;

public class ModuleService implements IModuleService {
    private static final IModuleRepository moduleRepository= new ModuleRepository();
    
    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Module select(int id) {
        return null;
    }

    @Override
    public boolean update(Module module) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void add(Module module) {

    }
}
