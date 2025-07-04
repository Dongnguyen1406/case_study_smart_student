package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Teacher;
import com.example.quan_ly_sinh_vien_codegym.repository.ITeacherRepository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TeacherRepository implements ITeacherRepository {
    private final String SELECT_TEACHER = "select teacher_name, dob, gender, address, number_phone, email from teachers;";
    
    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEACHER)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String teacherName = resultSet.getString("teacher_name");
                LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                String email = resultSet.getString("email");
                teachers.add(new Teacher(teacherName, dob, gender, address, numberPhone, email));
            }
        }catch (SQLException e){
            System.out.println("lỗi kết nối database");
        }
        return teachers;
    }

    @Override
    public Teacher select(int id) {
        return null;
    }

    @Override
    public boolean update(Teacher teacher) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void add(Teacher teacher) {

    }
}
