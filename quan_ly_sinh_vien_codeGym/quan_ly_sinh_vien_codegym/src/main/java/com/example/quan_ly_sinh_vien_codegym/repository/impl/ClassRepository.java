package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;
import com.example.quan_ly_sinh_vien_codegym.repository.IClassRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassRepository implements IClassRepository {
    private final String SELECT_CLASS = "select c.class_id, c.class_name, m.module_name, co.course_name, t.teacher_name , c.start_date, c.quantity_student\n" +
            "from classes c\n" +
            "join modules m on c.module_id = m.module_id\n" +
            "join courses co on co.course_id = c.course_id\n" +
            "join teachers t on t.teacher_id = c.teacher_id\n" +
            "where c.is_delete = 0";
    private final String DELETE_CLASS = "UPDATE classes SET is_delete = 1 WHERE class_id = ?";
    private final String UPDATE_CLASS = "UPDATE classes SET class_name = ?, module_id = ?, course_id = ?, teacher_id = (SELECT teacher_id FROM teachers WHERE teacher_name = ?), start_date = ?, quantity_student = ? WHERE class_id = ?";

    @Override
    public List<ClassResponseDto> findAll() {
        List<ClassResponseDto> classes = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int classId = Integer.parseInt(resultSet.getString("class_id")); 
                String className = resultSet.getString("class_name");
                String moduleName = resultSet.getString("module_name");
                String courseName = resultSet.getString("course_name");
                String teacherName = resultSet.getString("teacher_name");
                LocalDate startDate = LocalDate.parse(resultSet.getString("start_date"));
                int quantity = Integer.parseInt(resultSet.getString("quantity_student"));
                classes.add(new ClassResponseDto(classId, className, moduleName, courseName, teacherName, startDate, quantity));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối db");
        }
        return classes;
    }

    @Override
    public ClassResponseDto select(int id) {
        return null;
    }

    @Override
    public boolean update(ClassResponseDto classResponseDto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLASS)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa lớp: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void add(ClassResponseDto classResponseDto) {

    }
}
