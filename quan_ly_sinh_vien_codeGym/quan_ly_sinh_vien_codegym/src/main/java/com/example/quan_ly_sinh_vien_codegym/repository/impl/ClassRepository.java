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
    private final String SELECT_CLASS = "SELECT c.class_id, c.class_name, m.module_name, co.course_name, t.teacher_name, c.start_date, c.quantity_student " +
            "FROM classes c " +
            "JOIN modules m ON c.module_id = m.module_id " +
            "JOIN courses co ON co.course_id = c.course_id " +
            "JOIN teachers t ON t.teacher_id = c.teacher_id " +
            "WHERE c.is_delete = 0;";

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
            System.out.println("Lỗi kết nối db: " + e.getMessage());
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

    private static final String SELECT_BY_TEACHER = "SELECT c.class_id, c.class_name, m.module_name, co.course_name, t.teacher_name, c.start_date, c.quantity_student " +
            "FROM classes c " +
            "JOIN modules m ON c.module_id = m.module_id " +
            "JOIN courses co ON c.course_id = co.course_id " +
            "JOIN teachers t ON c.teacher_id = t.teacher_id " +
            "WHERE c.is_delete = 0 AND t.is_delete = 0 AND c.teacher_id = ?;";

    @Override
    public List<ClassResponseDto> findByTeacherId(String teacherId) {
        List<ClassResponseDto> classes = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TEACHER)) {
            preparedStatement.setString(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClassResponseDto dto = new ClassResponseDto();
                dto.setClassId(resultSet.getInt("class_id"));
                dto.setClassName(resultSet.getString("class_name"));
                dto.setModuleName(resultSet.getString("module_name"));
                dto.setCourseName(resultSet.getString("course_name"));
                dto.setTeacherName(resultSet.getString("teacher_name"));
                dto.setStartDate(resultSet.getDate("start_date").toLocalDate());
                classes.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách lớp: " + e.getMessage());
        }
        return classes;
    }
}