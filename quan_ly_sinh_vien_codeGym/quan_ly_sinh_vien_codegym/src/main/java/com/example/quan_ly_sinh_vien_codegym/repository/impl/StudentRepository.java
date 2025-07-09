package com.example.quan_ly_sinh_vien_codegym.repository.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.AttendanceDateDto;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleAttendance;
import com.example.quan_ly_sinh_vien_codegym.dto.StudentDto;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.dto.AssessmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class StudentRepository implements IStudentRepository {
    private final String SELECT_STUDENT_USERNAME = "SELECT s.student_id, s.student_name, s.dob, s.gender, s.address, s.number_phone, s.email, s.start_learn_date, c.class_name FROM students s JOIN classes c ON s.class_id = c.class_id JOIN accounts a ON s.student_id = a.student_id WHERE a.username = ?;";
    private final String UPDATE_STUDENT = "UPDATE students SET student_name = ?, dob = ?, gender = ?, address = ?, number_phone = ?, email = ? WHERE student_id = ?;";
    private final String SELECT_SCORE = "SELECT s.student_id, m.module_name, sc.quiz_score, sc.practice_score, sc.average_score FROM students s JOIN student_modules sm ON s.student_id = sm.student_id JOIN modules m ON sm.module_id = m.module_id JOIN scores sc ON sm.student_module_id = sc.student_module_id JOIN accounts a ON s.student_id = a.student_id WHERE a.username = ?;";
    private final String SELECT_ATTENDANCE = "SELECT s.student_id, sm.module_id, m.module_name, sm.registration_date, COUNT(CASE WHEN a.status_id = 1 THEN 1 END) AS unexcused_absences, CASE WHEN COUNT(CASE WHEN a.status_id = 1 THEN 1 END) >= 3 THEN 'Không đủ điều kiện thi' ELSE 'Đủ điều kiện thi' END AS result FROM students s JOIN student_modules sm ON s.student_id = sm.student_id JOIN modules m ON sm.module_id = m.module_id LEFT JOIN attendance a ON s.student_id = a.student_id JOIN accounts acc ON s.student_id = acc.student_id WHERE acc.username = ? GROUP BY s.student_id, sm.module_id, m.module_name, sm.registration_date;";
    private final String SELECT_ASSESSMENTS = "SELECT a.assessment_id, a.average_score, a.status, m.module_name FROM assessments a JOIN modules m ON a.module_id = m.module_id JOIN students s ON a.student_id = s.student_id JOIN accounts acc ON s.student_id = acc.student_id WHERE acc.username = ?;";
    private final String SELECT_MODULE_ATTENDANCE = "SELECT s.student_id, m.module_id, a.attendance_date, ast.status_name FROM students s JOIN attendance a ON s.student_id = a.student_id JOIN student_modules st ON s.student_id = st.student_id JOIN modules m ON st.module_id = m.module_id JOIN attendance_statuses ast ON a.status_id = ast.status_id WHERE s.student_id = ? AND m.module_id = ?;";

    private final String SELECT_STUDENT = "select s.student_id,s.student_name,s.dob,s.gender,s.address,s.number_phone,s.email,s.start_learn_date, c.class_name from students s join classes c on s.class_id=c.class_id WHERE s.is_delete = 0;";
    private final String SELECT_STUDENT_USERNAME = "select s.student_name,s.dob,s.gender,s.address,s.number_phone,s.email,s.start_learn_date, c.class_name from students s join classes c on s.class_id=c.class_id join accounts a on s.student_id=a.student_id where a.username=?;";
    private final String UPDATE_STUDENT = " update students  set student_name=?, dob=? ,gender=?,address=?,number_phone=?,email=?where student_id=?";
    private final String SELECT_SCORE = "select s.student_id,m.module_name, sc.quiz_score, sc.practice_score,sc.average_score from students s join student_modules sm on s.student_id= sm.student_id join modules m on sm.module_id=m.module_id join scores sc on sm.student_module_id=sc.student_module_id join accounts a on s.student_id=a.student_id where a.username=?;";
    private final String SELECT_ATTENDANCE = "CALL check_exam_eligibility(?);";
    private final String DELETE_STUDENT = "UPDATE students SET is_delete = 1 WHERE student_id = ?";
    private final String UPDATE_STUDENT_S =
            "UPDATE students SET student_name=?, dob=?, gender=?, address=?, number_phone=?, email=?, start_learn_Date=?, class_id=? WHERE student_id=?";


    private final String SELECT_BY_CLASS = "SELECT s.student_id, s.student_name, s.dob, s.gender, s.address, s.number_phone, s.email, s.start_learn_date, c.class_name FROM students s JOIN classes c ON s.class_id = c.class_id WHERE s.is_delete = 0 AND c.class_id = ?;";

    private final String SELECT_MODULE_ATTENDANCE = "select s.student_id,  m.module_name,a.attendance_date,ast.status_name  from students s \n" +
            "join attendance a on s.student_id=a.student_id \n" +
            "join student_modules st  on s.student_id=st.student_id\n" +
            "join modules m on st.module_id=m.module_id\n" +

            "join attendance_statuses ast on a.status_id=ast.status_id where s.student_id=? ;";
    
    
    private final String SELECT_STUDENT_DTO = "select s.student_id, s.student_name, s.gender, s.dob, s.address, s.email, s.number_phone, start_learn_date, c.class_id, c.class_name\n" +
            "from students s\n" +
            "join classes c on c.class_id = s.class_id\n" +
            "where c.is_delete = false and s.is_delete = false;";

    private final String INSERT_STUDENT = "INSERT INTO students (student_id, student_name, dob, gender, address, number_phone, email, start_learn_date, class_id, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 2);";
    
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
            System.out.println("lỗi kết nối database");
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
    public List<StudentDto> findAllDto() {
        List<StudentDto> studentDtos = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_DTO)) {
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
                Integer classId = resultSet.getInt("class_id");
                String className = resultSet.getString("class_name");
                studentDtos.add(new StudentDto(studentId, studentName, dob, gender, address, numberPhone, email, classId, startLearnDate,className));

            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }
        return studentDtos;
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
            preparedStatement.setDate(7, java.sql.Date.valueOf(student.getStartLearnDate()));
            preparedStatement.setInt(8, student.getClassId());
            preparedStatement.setString(9, student.getStudentId());

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
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(student.getDob()));
            preparedStatement.setString(4, student.getGender());
            preparedStatement.setString(5, student.getAddress());
            preparedStatement.setString(6, student.getNumberPhone());
            preparedStatement.setString(7, student.getEmail());
            preparedStatement.setDate(8, java.sql.Date.valueOf(student.getStartLearnDate()));
            preparedStatement.setInt(9, student.getClassId()); 

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm học sinh thành công!");
            }

        } catch (SQLException e) {
//            System.out.println("Lỗi khi thêm học sinh: " + e.getMessage());
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("Học sinh đã tồn tại!");
            } else {
                e.printStackTrace(); // In chi tiết hơn
            }
        }
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
                String module = resultSet.getString("module_name");
                Double quizScore = resultSet.getDouble("quiz_score");
                Double practiceScore = resultSet.getDouble("practice_score");
                Double averageScore = resultSet.getDouble("average_score");
                moduleScore = new ModuleScore(module, quizScore, practiceScore, averageScore);
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }

        return moduleScore;
    }

    @Override
    public List<AttendanceDateDto>  displayAttendanceDate(String idStudent) {
        List<AttendanceDateDto> attendanceDateDto = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MODULE_ATTENDANCE)) {
            preparedStatement.setString(1, idStudent);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String moduleName = resultSet.getString("module_name");
                LocalDate attendanceDate = LocalDate.parse(resultSet.getString("attendance_date"));
                String statusName = resultSet.getString("status_name");
                 attendanceDateDto.add(new AttendanceDateDto(moduleName, studentId, attendanceDate, statusName));
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }

        return attendanceDateDto;
    }

    public ModuleAttendance displayAttendance(String userName) {
        ModuleAttendance moduleAttendance = null;
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTENDANCE)) {

            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                int moduleId = resultSet.getInt("module_id");
                String moduleName = resultSet.getString("module_name");
                LocalDate registrationDate = LocalDate.parse(resultSet.getString("registration_date"));
                int unexcusedAbsences = resultSet.getInt("unexcused_absences");
                String result = resultSet.getString("result");
                moduleAttendance = new ModuleAttendance(studentId, moduleId, moduleName, registrationDate, unexcusedAbsences, result);
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
        }

        return moduleAttendance;
    }

    @Override
    public List<AssessmentDto> displayAssessments(String userName) {
        List<AssessmentDto> assessments = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ASSESSMENTS)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int assessmentId = resultSet.getInt("assessment_id");
                double averageScore = resultSet.getDouble("average_score");
                boolean status = resultSet.getBoolean("status");
                String moduleName = resultSet.getString("module_name");
                
                AssessmentDto assessment = new AssessmentDto();
                assessment.setAssessmentId(assessmentId);
                assessment.setAverageScore(averageScore);
                assessment.setStatus(status);
                assessment.setModuleName(moduleName);
                
                assessments.add(assessment);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return assessments;
    }
}
}


