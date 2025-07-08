package com.example.quan_ly_sinh_vien_codegym.repository;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;

import java.util.List;
import java.util.Map;

public interface IScoreRepository {
    void saveScores(List<ModuleScore> scores); // Thêm phương thức saveScores
    Map<String, ModuleScore> findScoresByClassAndModule(int classId, int moduleId);
    void deleteScoresByClassAndModule(int classId, int moduleId);
    void deleteStudentScoreByModuleId(String studentId, int moduleId);
}