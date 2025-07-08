package com.example.quan_ly_sinh_vien_codegym.entity;

public class Assessment {
    int assessmentId;
    double averageScore;
    String studentId;
    int moduleId;
    Boolean status;

    public Assessment() {
    }

    public Assessment(int assessmentId, double averageScore, String studentId, int moduleId, Boolean status) {
        this.assessmentId = assessmentId;
        this.averageScore = averageScore;
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.status = status;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}