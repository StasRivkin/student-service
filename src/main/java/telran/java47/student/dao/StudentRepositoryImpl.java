package telran.java47.student.dao;

import org.springframework.stereotype.Component;
import telran.java47.student.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StudentRepositoryImpl implements StudentRepository {
    Map<Integer, Student> students = new ConcurrentHashMap<>();

    @Override
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public void deleteById(int id) {
        students.remove(id);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}
