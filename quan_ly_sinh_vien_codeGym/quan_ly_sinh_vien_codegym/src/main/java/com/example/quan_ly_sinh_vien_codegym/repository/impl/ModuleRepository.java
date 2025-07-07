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
    private final String UPDATE_MODULE =
            "UPDATE modules SET module_name=? WHERE module_id=?";
    private final String DELETE_MODULE = "DELETE FROM modules WHERE module_id = ?";
    private final String INSERT_MODULE = "INSERT INTO modules (module_name) VALUES (?)";
    
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
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MODULE)) {
            preparedStatement.setString(1, module.getModuleName());
            preparedStatement.setInt(2, module.getModuleId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật học sinh: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MODULE)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa module: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void add(Module module) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MODULE)) {
            preparedStatement.setString(1, module.getModuleName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm khóa học: " + e.getMessage());
        }
    }
}
