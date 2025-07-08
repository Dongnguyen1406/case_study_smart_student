package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.repository.IScoreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreRepository implements IScoreRepository {
    private final String INSERT_SCORE = "INSERT INTO scores (student_module_id, quiz_score, practice_score, average_score) " +
            "SELECT sm.student_module_id, ?, ?, (? + ?) / 2 FROM student_modules sm " +
            "WHERE sm.student_id = ? AND sm.module_id = ? " +
            "ON DUPLICATE KEY UPDATE " +
            "quiz_score = VALUES(quiz_score), " +
            "practice_score = VALUES(practice_score), " +
            "average_score = VALUES(average_score);";
    
    private final String SELECT_SCORES_BY_CLASS_MODULE = 
            "SELECT s.student_id, sc.quiz_score, sc.practice_score, sc.average_score " +
            "FROM students s " +
            "JOIN student_modules sm ON s.student_id = sm.student_id " +
            "JOIN scores sc ON sm.student_module_id = sc.student_module_id " +
            "WHERE s.class_id = ? AND sm.module_id = ?;";
            
    private final String DELETE_SCORES_BY_CLASS_MODULE = 
            "DELETE sc FROM scores sc " +
            "JOIN student_modules sm ON sc.student_module_id = sm.student_module_id " +
            "JOIN students s ON sm.student_id = s.student_id " +
            "WHERE s.class_id = ? AND sm.module_id = ?;";
            
    private final String DELETE_STUDENT_SCORE_BY_MODULE_ID = 
            "DELETE sc FROM scores sc " +
            "JOIN student_modules sm ON sc.student_module_id = sm.student_module_id " +
            "WHERE sm.student_id = ? AND sm.module_id = ?;";

    private final String INSERT_STUDENT_MODULE = "INSERT INTO student_modules (student_id, module_id, registration_date, status) " +
            "SELECT ?, ?, CURRENT_DATE, 1 " +
            "FROM students s " +
            "WHERE s.student_id = ? " +
            "AND NOT EXISTS (" +
            "    SELECT 1 FROM student_modules sm " +
            "    WHERE sm.student_id = ? AND sm.module_id = ?" +
            ");";

    private void ensureStudentModuleExists(Connection connection, String studentId, int moduleId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_MODULE)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setInt(2, moduleId);
            preparedStatement.setString(3, studentId);
            preparedStatement.setString(4, studentId);
            preparedStatement.setInt(5, moduleId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void saveScores(List<ModuleScore> scores) {
        try (Connection connection = BaseRepository.getConnectDB()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCORE)) {
                for (ModuleScore score : scores) {
                    // First, ensure the student is registered for the module
                    ensureStudentModuleExists(connection, score.getStudentId(), score.getModuleId());

                    preparedStatement.setDouble(1, score.getQuizScore());
                    preparedStatement.setDouble(2, score.getPracticeScore());
                    preparedStatement.setDouble(3, score.getQuizScore());
                    preparedStatement.setDouble(4, score.getPracticeScore());
                    preparedStatement.setString(5, score.getStudentId());
                    preparedStatement.setInt(6, score.getModuleId());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu điểm: " + e.getMessage());
            throw new RuntimeException("Không thể lưu điểm", e);
        }
    }
    
    @Override
    public Map<String, ModuleScore> findScoresByClassAndModule(int classId, int moduleId) {
        Map<String, ModuleScore> scores = new HashMap<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SCORES_BY_CLASS_MODULE)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, moduleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                double quizScore = resultSet.getDouble("quiz_score");
                double practiceScore = resultSet.getDouble("practice_score");
                double averageScore = resultSet.getDouble("average_score");
                
                ModuleScore score = new ModuleScore(null, quizScore, practiceScore, averageScore);
                score.setStudentId(studentId);
                score.setModuleId(moduleId);
                scores.put(studentId, score);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy điểm: " + e.getMessage());
        }
        return scores;
    }
    
    @Override
    public void deleteScoresByClassAndModule(int classId, int moduleId) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SCORES_BY_CLASS_MODULE)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, moduleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa điểm: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteStudentScoreByModuleId(String studentId, int moduleId) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SCORE_BY_MODULE_ID)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setInt(2, moduleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa điểm của học sinh: " + e.getMessage());
        }
    }
}