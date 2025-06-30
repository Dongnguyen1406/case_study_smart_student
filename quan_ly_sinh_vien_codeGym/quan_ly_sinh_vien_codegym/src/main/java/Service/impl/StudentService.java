package Service.impl;

import Entity.Student;
import Repository.IStudentRepository;
import Repository.impl.StudentRepository;
import Service.IStudentService;

import java.security.Provider;
import java.util.List;

public class StudentService implements IStudentService {
    private static final IStudentRepository iStudentRepository= new StudentRepository();
    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }
}
