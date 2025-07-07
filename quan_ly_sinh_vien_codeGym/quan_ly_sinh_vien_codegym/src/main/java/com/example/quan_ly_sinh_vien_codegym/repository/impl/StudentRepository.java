package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.AttendanceDateDto;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleAttendance;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final String SELECT_STUDENT = "SELECT s.student_id, s.student_name, s.dob, s.gender, s.address, s.number_phone, s.email, s.start_learn_date, c.class_name FROM students s JOIN classes c ON s.class_id = c.class_id WHERE s.is_delete = 0;";
    private final String SELECT_STUDENT_USERNAME = "SELECT s.student_id, s.student_name, s.dob, s.gender, s.address, s.number_phone, s.email, s.start_learn_date, c.class_name FROM students s JOIN classes c ON s.class_id = c.class_id JOIN accounts a ON s.student_id = a.student_id WHERE a.username = ?;";
    private final String UPDATE_STUDENT = "UPDATE students SET student_name = ?, dob = ?, gender = ?, address = ?, number_phone = ?, email = ? WHERE student_id = ?;";
    private final String SELECT_SCORE = "SELECT s.student_id, m.module_name, sc.quiz_score, sc.practice_score, sc.average_score FROM students s JOIN student_modules sm ON s.student_id = sm.student_id JOIN modules m ON sm.module_id = m.module_id JOIN scores sc ON sm.student_module_id = sc.student_module_id JOIN accounts a ON s.student_id = a.student_id WHERE a.username = ?;";
    private final String SELECT_ATTENDANCE = "CALL check_exam_eligibility(?);";
    private final String SELECT_MODULE_ATTENDANCE = "SELECT s.student_id, m.module_id, a.attendance_date, ast.status_name FROM students s JOIN attendance a ON s.student_id = a.student_id JOIN student_modules st ON s.student_id = st.student_id JOIN modules m ON st.module_id = m.module_id JOIN attendance_statuses ast ON a.status_id = ast.status_id WHERE s.student_id = ? AND m.module_id = ?;";
    private final String DELETE_STUDENT = "UPDATE students SET is_delete = 1 WHERE student_id = ?;";
    private final String UPDATE_STUDENT_S = "UPDATE students SET student_name = ?, dob = ?, gender = ?, address = ?, number_phone = ?, email = ?, status = ? WHERE student_id = ?;";
    private final String SELECT_BY_CLASS = "SELECT s.student_id, s.student_name, s.dob, s.gender, s.address, s.number_phone, s.email, s.start_learn_date, c.class_name FROM students s JOIN classes c ON s.class_id = c.class_id WHERE s.is_delete = 0 AND c.class_id = ?;";

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String studentName = resultSet.getString("student_name");
                LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                String email = resultSet.getString("email");
                LocalDate startLearnDate = LocalDate.parse(resultSet.getString("start_learn_date"));
                String className = resultSet.getString("class_name");
                students.add(new Student(studentId, studentName, dob, gender, address, numberPhone, email, startLearnDate, className));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return students;
    }

    @Override
    public Student select(int id) {
        return null;
    }

    @Override
    public List<Student> findByClassId(int classId) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_CLASS)) {
            preparedStatement.setInt(1, classId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String studentName = resultSet.getString("student_name");
                LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                String email = resultSet.getString("email");
                LocalDate startLearnDate = LocalDate.parse(resultSet.getString("start_learn_date"));
                String className = resultSet.getString("class_name");
                students.add(new Student(studentId, studentName, dob, gender, address, numberPhone, email, startLearnDate, className));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm học sinh theo lớp: " + e.getMessage());
        }
        return students;
    }

    @Override
    public boolean update(Student student) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_S)) {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(student.getDob()));
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getNumberPhone());
            preparedStatement.setString(6, student.getEmail());
            preparedStatement.setBoolean(7, student.getStatus());
            preparedStatement.setString(8, student.getStudentId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật học sinh: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setString(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa học sinh: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void add(Student student) {
    }

    @Override
    public Student displayStudent(String username) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String studentName = resultSet.getString("student_name");
                LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                String email = resultSet.getString("email");
                LocalDate startLearnDate = LocalDate.parse(resultSet.getString("start_learn_date"));
                String className = resultSet.getString("class_name");
                return new Student(studentId, studentName, dob, gender, address, numberPhone, email, startLearnDate, className);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateStudent(Student student) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(student.getDob()));
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getNumberPhone());
            preparedStatement.setString(6, student.getEmail());
            preparedStatement.setString(7, student.getStudentId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return false;
    }

    @Override
    public ModuleScore displayScore(String userName) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SCORE)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String module = resultSet.getString("module_name");
                double quizScore = resultSet.getDouble("quiz_score");
                double practiceScore = resultSet.getDouble("practice_score");
                double averageScore = resultSet.getDouble("average_score");
                return new ModuleScore(module, quizScore, practiceScore, averageScore);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return null;
    }

    @Override
    public AttendanceDateDto displayAttendanceDate(String id) {
        return null;
    }

    @Override
    public ModuleAttendance displayAttendance(String userName) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTENDANCE)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                int moduleId = resultSet.getInt("module_id");
                String moduleName = resultSet.getString("module_name");
                LocalDate registrationDate = LocalDate.parse(resultSet.getString("registration_date"));
                int unexcusedAbsences = resultSet.getInt("unexcused_absences");
                String result = resultSet.getString("result");
                return new ModuleAttendance(studentId, moduleId, moduleName, registrationDate, unexcusedAbsences, result);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return null;
    }
}