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
    private final String SELECT_TEACHER = "select teacher_id, teacher_name, dob, gender, address, number_phone, email, status from teachers WHERE is_delete = 0;";
    private final String UPDATE_TEACHER =
            "UPDATE teachers SET teacher_name=?, dob=?, gender=?, address=?, number_phone=?, email=?, status=? WHERE teacher_id=?";
    private final String DELETE_TEACHER = "UPDATE teachers SET is_delete = 1 WHERE teacher_id = ?";

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEACHER)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String teacherId = resultSet.getString("teacher_id");
                String teacherName = resultSet.getString("teacher_name");
                LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                String email = resultSet.getString("email");
                teachers.add(new Teacher(teacherId, teacherName, dob, gender, address, numberPhone, email));
            }
        } catch (SQLException e) {
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
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEACHER)) {
            preparedStatement.setString(1, teacher.getTeacherName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(teacher.getDob()));
            preparedStatement.setString(3, teacher.getGender());
            preparedStatement.setString(4, teacher.getAddress());
            preparedStatement.setString(5, teacher.getNumberPhone());
            preparedStatement.setString(6, teacher.getEmail());
            preparedStatement.setBoolean(7, teacher.getStatus()); // thêm dòng này nếu có cột status
            preparedStatement.setString(8, teacher.getTeacherId());

            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật học sinh: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TEACHER)) {
            preparedStatement.setString(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa giáo viên: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void add(Teacher teacher) {

    }
}
