package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.repository.IAssessmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssessmentRepository implements IAssessmentRepository {
    private final String INSERT_ASSESSMENT = "INSERT INTO assessments (average_score, module_id, student_id, status) VALUES (?, ?, ?, ?);";
    
    private final String SELECT_ASSESSMENTS_BY_CLASS_MODULE = 
            "SELECT a.assessment_id, a.average_score, a.module_id, a.student_id, a.status " +
            "FROM assessments a " +
            "JOIN students s ON a.student_id = s.student_id " +
            "WHERE s.class_id = ? AND a.module_id = ?;";
            
    private final String DELETE_ASSESSMENTS_BY_CLASS_MODULE = 
            "DELETE a FROM assessments a " +
            "JOIN students s ON a.student_id = s.student_id " +
            "WHERE s.class_id = ? AND a.module_id = ?;";
            
    private final String DELETE_STUDENT_ASSESSMENT_BY_MODULE_ID = 
            "DELETE FROM assessments WHERE student_id = ? AND module_id = ?;";

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
    
    @Override
    public Map<String, Assessment> findAssessmentsByClassAndModule(int classId, int moduleId) {
        Map<String, Assessment> assessments = new HashMap<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ASSESSMENTS_BY_CLASS_MODULE)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, moduleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int assessmentId = resultSet.getInt("assessment_id");
                double averageScore = resultSet.getDouble("average_score");
                String studentId = resultSet.getString("student_id");
                boolean status = resultSet.getBoolean("status");
                
                Assessment assessment = new Assessment(assessmentId, averageScore, studentId, moduleId, status);
                assessments.put(studentId, assessment);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy đánh giá: " + e.getMessage());
        }
        return assessments;
    }
    
    @Override
    public void deleteAssessmentsByClassAndModule(int classId, int moduleId) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ASSESSMENTS_BY_CLASS_MODULE)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, moduleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa đánh giá: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteStudentAssessmentByModuleId(String studentId, int moduleId) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_ASSESSMENT_BY_MODULE_ID)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setInt(2, moduleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa đánh giá của học sinh: " + e.getMessage());
        }
    }
}