package com.example.quan_ly_sinh_vien_codegym.dto;

public class ModuleScore {
    String module;
    double quizScore;
    double practiceScore;
    double averageScore;

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
}