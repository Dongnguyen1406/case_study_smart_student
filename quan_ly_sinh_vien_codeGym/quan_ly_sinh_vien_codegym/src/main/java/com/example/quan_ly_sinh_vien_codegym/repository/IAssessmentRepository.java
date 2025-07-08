package com.example.quan_ly_sinh_vien_codegym.repository;

import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;

import java.util.List;

public interface IAssessmentRepository {
    void saveAssessments(List<Assessment> assessments);
}