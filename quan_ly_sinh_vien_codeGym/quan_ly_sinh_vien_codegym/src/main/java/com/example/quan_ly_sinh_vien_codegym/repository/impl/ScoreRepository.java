package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.repository.IScoreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ScoreRepository implements IScoreRepository {
    private final String INSERT_SCORE = "INSERT INTO scores (student_module_id, quiz_score, practice_score) " +
            "SELECT sm.student_module_id, ?, ? FROM student_modules sm " +
            "WHERE sm.student_id = ? AND sm.module_id = ?;";

    @Override
    public void saveScores(List<ModuleScore> scores) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCORE)) {
            for (ModuleScore score : scores) {
                preparedStatement.setDouble(1, score.getQuizScore());
                preparedStatement.setDouble(2, score.getPracticeScore());
                preparedStatement.setString(3, score.getStudentId());
                preparedStatement.setInt(4, score.getModuleId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu điểm: " + e.getMessage());
        }
    }
}