package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.repository.IAccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository implements IAccountRepository {
    @Override
    public Account findByUsername(String username) {
        String sql = "select * from accounts where username = ?";

        try (Connection connection =BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Account account = new Account();
                account.setAccountId(resultSet.getInt("account_id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setToken(resultSet.getString("token"));
                account.setStatus(resultSet.getBoolean("status"));
                account.setRoleId(resultSet.getInt("role_id"));
                account.setStudentId(resultSet.getString("student_id"));
                account.setTeacherId(resultSet.getString("teacher_id"));

                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }



}
