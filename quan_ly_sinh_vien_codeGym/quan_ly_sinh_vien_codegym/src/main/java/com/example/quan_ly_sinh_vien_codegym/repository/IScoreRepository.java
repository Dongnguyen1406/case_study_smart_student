package com.example.quan_ly_sinh_vien_codegym.repository;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;

import java.util.List;

public interface IScoreRepository {
    void saveScores(List<ModuleScore> scores); // Thêm phương thức saveScores
}