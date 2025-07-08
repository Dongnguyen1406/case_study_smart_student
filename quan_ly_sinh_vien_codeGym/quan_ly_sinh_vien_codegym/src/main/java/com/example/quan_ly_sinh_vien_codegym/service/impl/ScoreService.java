package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.repository.IScoreRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.ScoreRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.StudentRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IScoreService;

import java.util.List;

public class ScoreService implements IScoreService {
    private final IScoreRepository scoreRepository = new ScoreRepository();
    private final IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> findStudentsByClassAndModule(int classId, int moduleId) {
        return studentRepository.findByClassId(classId);
    }

    @Override
    public void saveScores(List<ModuleScore> scores) {
        scoreRepository.saveScores(scores);
    }
}