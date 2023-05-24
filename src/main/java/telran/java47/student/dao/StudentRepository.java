package telran.java47.student.dao;

import telran.java47.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    Optional<Student> findById(int id);

    void deleteById(int id);

    List<Student> findAll();
}
