package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;

import java.util.List;

public interface IScoreService {
    List<Student> findStudentsByClassAndModule(int classId, int moduleId);
    void saveScores(List<ModuleScore> scores);
}