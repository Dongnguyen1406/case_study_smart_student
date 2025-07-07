package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.repository.IAssessmentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.AssessmentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.StudentRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IAssessmentService;

import java.util.List;

public class AssessmentService implements IAssessmentService {
    private final IAssessmentRepository assessmentRepository = new AssessmentRepository();
    private final IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> findStudentsByClassAndModule(int classId, int moduleId) {
        return studentRepository.findByClassId(classId);
    }

    @Override
    public void saveAssessments(List<Assessment> assessments) {
        assessmentRepository.saveAssessments(assessments);
    }
}