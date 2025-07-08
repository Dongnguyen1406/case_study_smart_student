package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
import com.example.quan_ly_sinh_vien_codegym.entity.AttendanceStatus;
import com.example.quan_ly_sinh_vien_codegym.repository.IAttendanceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository implements IAttendanceRepository {
    private final String SELECT_STATUSES = "SELECT status_id, status_name FROM attendance_statuses;";
    private final String INSERT_ATTENDANCE = "INSERT INTO attendance (student_id, attendance_date, status_id) VALUES (?, ?, ?);";

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
}