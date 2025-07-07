package com.example.quan_ly_sinh_vien_codegym.dto;

import java.time.LocalDate;

public class AttendanceDateDto {
    String moduleName;
    int moduleId;
    String studentId;
    LocalDate attendanceDate;
    String status;

    public AttendanceDateDto(String moduleName, int moduleId, String studentId, LocalDate attendanceDate, String status) {
        this.moduleName = moduleName;
        this.moduleId = moduleId;
        this.studentId = studentId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
