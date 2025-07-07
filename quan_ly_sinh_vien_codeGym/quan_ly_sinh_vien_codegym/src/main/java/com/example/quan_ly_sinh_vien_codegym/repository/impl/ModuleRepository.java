package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Module;
import com.example.quan_ly_sinh_vien_codegym.repository.IModuleRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleRepository implements IModuleRepository {

    private final String SELECT_MODULE = "select module_id, module_name from modules;";

    @Override
    public List<Module> findAll() {
        List<Module> modules = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MODULE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int moduleId = resultSet.getInt("module_id");
                String moduleName = resultSet.getString("module_name");
                modules.add(new Module(moduleId, moduleName));
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }
        return modules;
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
