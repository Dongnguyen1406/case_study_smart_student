package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
import com.example.quan_ly_sinh_vien_codegym.entity.AttendanceStatus;
import com.example.quan_ly_sinh_vien_codegym.repository.IAttendanceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceRepository implements IAttendanceRepository {
    private final String SELECT_STATUSES = "SELECT status_id, status_name FROM attendance_statuses;";
    private final String INSERT_ATTENDANCE = "INSERT INTO attendance (student_id, attendance_date, status_id) VALUES (?, ?, ?);";
    
    private final String SELECT_ATTENDANCE_BY_CLASS_DATE = 
            "SELECT a.student_id, a.status_id " +
            "FROM attendance a " +
            "JOIN students s ON a.student_id = s.student_id " +
            "WHERE s.class_id = ? AND a.attendance_date = ?;";
            
    private final String DELETE_ATTENDANCE_BY_CLASS_DATE = 
            "DELETE a FROM attendance a " +
            "JOIN students s ON a.student_id = s.student_id " +
            "WHERE s.class_id = ? AND a.attendance_date = ?;";
            
    private final String DELETE_STUDENT_ATTENDANCE_BY_DATE = 
            "DELETE FROM attendance WHERE student_id = ? AND attendance_date = ?;";

    @Override
    public List<AttendanceStatus> findAllStatuses() {
        List<AttendanceStatus> statuses = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATUSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int statusId = resultSet.getInt("status_id");
                String statusName = resultSet.getString("status_name");
                statuses.add(new AttendanceStatus(statusId, statusName));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return statuses;
    }

    @Override
    public void saveAttendance(List<Attendance> attendances) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTENDANCE)) {
            for (Attendance attendance : attendances) {
                preparedStatement.setString(1, attendance.getStudentId());
                preparedStatement.setDate(2, java.sql.Date.valueOf(attendance.getAttendanceDate()));
                preparedStatement.setInt(3, attendance.getStatusId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu điểm danh: " + e.getMessage());
        }
    }
    
    @Override
    public Map<String, Integer> findAttendanceByClassAndDate(int classId, LocalDate date) {
        Map<String, Integer> attendanceMap = new HashMap<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTENDANCE_BY_CLASS_DATE)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                int statusId = resultSet.getInt("status_id");
                attendanceMap.put(studentId, statusId);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy điểm danh: " + e.getMessage());
        }
        return attendanceMap;
    }
    
    @Override
    public void deleteAttendanceByClassAndDate(int classId, LocalDate date) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ATTENDANCE_BY_CLASS_DATE)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa điểm danh: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteStudentAttendanceByDate(String studentId, LocalDate date) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_ATTENDANCE_BY_DATE)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa điểm danh của học sinh: " + e.getMessage());
        }
    }
}