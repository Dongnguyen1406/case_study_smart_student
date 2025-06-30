package Repository.impl;

import Entity.Student;
import Repository.IStudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final String SELECT_STUDENT = "select s.student_name,s.dob,s.gender,s.address,s.number_phone,s.email,s.start_learn_date, c.class_name from students s join classes c on s.class_id=c.class_id;";

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
}
