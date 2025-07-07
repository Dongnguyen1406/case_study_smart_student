package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Course;
import com.example.quan_ly_sinh_vien_codegym.repository.ICourseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements ICourseRepository {
    private final String SELECT_COURSE = "select course_id, course_name from courses;";
    private final String UPDATE_COURSE =
            "UPDATE courses SET course_name=? WHERE course_id=?";
    private final String DELETE_COURSE = "DELETE FROM courses WHERE course_id = ?";
    private final String INSERT_COURSE = "INSERT INTO courses (course_name) VALUES (?)";

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                courses.add(new Course(courseId, courseName));
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
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE)) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getCourseId());
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa course: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void add(Course course) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE)) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm khóa học: " + e.getMessage());
        }
    }
}
