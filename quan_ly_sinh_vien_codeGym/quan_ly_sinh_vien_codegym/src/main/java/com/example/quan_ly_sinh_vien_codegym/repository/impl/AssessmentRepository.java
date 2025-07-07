package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.repository.IAssessmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AssessmentRepository implements IAssessmentRepository {
    private final String INSERT_ASSESSMENT = "INSERT INTO assessments (average_score, module_id, student_id, status) VALUES (?, ?, ?, ?);";

    @Override
    public void saveAssessments(List<Assessment> assessments) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ASSESSMENT)) {
            for (Assessment assessment : assessments) {
                preparedStatement.setDouble(1, assessment.getAverageScore());
                preparedStatement.setInt(2, assessment.getModuleId());
                preparedStatement.setString(3, assessment.getStudentId());
                preparedStatement.setBoolean(4, assessment.getStatus());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu đánh giá: " + e.getMessage());
        }
    }
}