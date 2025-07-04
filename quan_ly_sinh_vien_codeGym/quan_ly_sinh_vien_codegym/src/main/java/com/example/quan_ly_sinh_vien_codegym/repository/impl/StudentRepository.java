package com.example.quan_ly_sinh_vien_codegym.repository.impl;

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
    private final String SELECT_STUDENT = "select s.student_name,s.dob,s.gender,s.address,s.number_phone,s.email,s.start_learn_date, c.class_name from students s join classes c on s.class_id=c.class_id;";
    private final String SELECT_STUDENT_USERNAME = "select s.student_name,s.dob,s.gender,s.address,s.number_phone,s.email,s.start_learn_date, c.class_name from students s join classes c on s.class_id=c.class_id join accounts a on s.student_id=a.student_id where a.username=?;";
    private final String UPDATE_STUDENT = " update students  set student_name=?, dob=? ,gender=?,address=?,number_phone=?,email=?where student_id=?";
    private final String SELECT_SCORE = "select s.student_id,m.module_name, sc.quiz_score, sc.practice_score,sc.average_score from students s join student_modules sm on s.student_id= sm.student_id join modules m on sm.module_id=m.module_id join scores sc on sm.student_module_id=sc.student_module_id join accounts a on s.student_id=a.student_id where a.username=?;";
    private final String SELECT_ATTENDANCE = "CALL check_exam_eligibility(?);";
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentName = resultSet.getString("student_name");
                LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                String email = resultSet.getString("email");
                LocalDate startLearnDate = LocalDate.parse(resultSet.getString("start_learn_date"));
                String className = resultSet.getString("class_name");
                students.add(new Student(studentName, dob, gender, address, numberPhone, email, startLearnDate, className));

            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }
        return students;
    }

    @Override
    public Student select(int id) {
        return null;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void add(Student student) {

    }

    public Student displayStudent(String username) {
        Student student = null;
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String studentName = resultSet.getString("student_name");
                LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                String email = resultSet.getString("email");
                LocalDate startLearnDate = LocalDate.parse(resultSet.getString("start_learn_date"));
                String className = resultSet.getString("class_name");
                student = new Student(studentName, dob, gender, address, numberPhone, email, startLearnDate, className);

            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }

        return student;
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
            System.out.println("lỗi kết nối database");
        }
        return false;
    }


    public ModuleScore displayScore(String userName) {
        ModuleScore moduleScore = null;
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SCORE)) {

            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               String module= resultSet.getString("module_name");
             Double quizScore=resultSet.getDouble("quiz_score");
             Double practiceScore=resultSet.getDouble("practice_score");
             Double averageScore=resultSet.getDouble("average_score");
             moduleScore =new ModuleScore(module,quizScore,practiceScore,averageScore);
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }

        return moduleScore;
    }
    public ModuleAttendance displayAttendance(String userName){
        ModuleAttendance moduleAttendance = null;
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTENDANCE)) {

            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String studentId= resultSet.getString("student_id");
                int moduleId=resultSet.getInt("module_id");
                String moduleName=resultSet.getString("module_name");
                LocalDate registrationDate= LocalDate.parse(resultSet.getString("registration_date"));
                int unexcusedAbsences=resultSet.getInt("unexcused_absences");
                String result=resultSet.getString("result");
                moduleAttendance =new ModuleAttendance(studentId,moduleId,moduleName,registrationDate,unexcusedAbsences,result);
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }

        return moduleAttendance;
    }
}


