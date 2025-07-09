package com.example.quan_ly_sinh_vien_codegym.dto;

public class ModuleScore {
    String module;
    double quizScore;
    double practiceScore;
    double averageScore;
    String studentId; // Thêm
    int moduleId;     // Thêm

    public ModuleScore(String studentId, int moduleId, double quizScore, double practiceScore) {
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.quizScore = quizScore;
        this.practiceScore = practiceScore;
        this.averageScore = (quizScore + practiceScore) / 2;
    }

    public ModuleScore(String module, double quizScore, double practiceScore, double averageScore) {
        this.module = module;
        this.quizScore = quizScore;
        this.practiceScore = practiceScore;
        this.averageScore = averageScore;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public double getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(double quizScore) {
        this.quizScore = quizScore;
    }

    public double getPracticeScore() {
        return practiceScore;
    }

    public void setPracticeScore(double practiceScore) {
        this.practiceScore = practiceScore;
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
}