package com.example.quan_ly_sinh_vien_codegym.dto;

import java.time.LocalDate;

public class ModuleAttendance {
    String studentId;
    int moduleId;
    String moduleName;
    LocalDate registrationDate;
    int unexcusedAbsences;
    String result;
    public ModuleAttendance(String studentId, int moduleId, String moduleName, LocalDate registrationDate, int unexcusedAbsences, String result) {
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.registrationDate = registrationDate;
        this.unexcusedAbsences = unexcusedAbsences;
        this.result = result;

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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getUnexcusedAbsences() {
        return unexcusedAbsences;
    }

    public void setUnexcusedAbsences(int unexcusedAbsences) {
        this.unexcusedAbsences = unexcusedAbsences;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}