package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;

import java.util.List;
import java.util.Map;

public interface IScoreService {
    List<Student> findStudentsByClassAndModule(int classId, int moduleId);
    void saveScores(List<ModuleScore> scores);
    Map<String, ModuleScore> findScoresByClassAndModule(int classId, int moduleId);
    void deleteScoresByClassAndModule(int classId, int moduleId);
    void deleteStudentScoreByModuleId(String studentId, int moduleId);
}