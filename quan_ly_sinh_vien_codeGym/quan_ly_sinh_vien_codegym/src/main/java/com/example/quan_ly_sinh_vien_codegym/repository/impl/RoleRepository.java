package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Role;
import com.example.quan_ly_sinh_vien_codegym.repository.IRoleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements IRoleRepository {
    private final String SElECT_ROLE = "select role_id,role_name from roles";

    @Override
    public List<Role> findAll() {
        List<Role> roles= new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SElECT_ROLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
          int id=    resultSet.getInt("role_id");
           String name=   resultSet.getString("role_name");
              roles.add(new Role(id,name));
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }
        return roles;
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
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void add(Role role) {

    }
}
