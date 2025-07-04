package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Course;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.repository.ICourseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements ICourseRepository {
    private final String SELECT_COURSE = "select course_name from courses;";
    
    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                courses.add(new Course(courseName));
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }
        return courses;
    }

    @Override
    public Course select(int id) {
        return null;
    }

    @Override
    public boolean update(Course course) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void add(Course course) {

    }
}
