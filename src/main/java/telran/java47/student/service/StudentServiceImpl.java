package telran.java47.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telran.java47.student.dao.StudentRepository;
import telran.java47.student.dto.ScoreDto;
import telran.java47.student.dto.StudentCreateDto;
import telran.java47.student.dto.StudentDto;
import telran.java47.student.dto.StudentUpdateDto;
import telran.java47.student.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean addStudent(StudentCreateDto studentCreateDto) {
        if (studentRepository.findById(studentCreateDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(),
                studentCreateDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student == null ? null : new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(int id) {
        StudentDto student = findStudent(id);
        if (student != null) {
            studentRepository.deleteById(id);
            return new StudentDto(student.getId(), student.getName(), student.getScores());
        }
        return null;
    }

    @Override
    public StudentCreateDto updateStudent(int id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentUpdateDto.getName());
            student.setPassword(studentUpdateDto.getPassword());
            studentRepository.save(student);
        }
        return student != null ? new StudentCreateDto(student.getId(), student.getName(), student.getPassword()) : null;
    }

    @Override
    public boolean addScore(int id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
//            student.addScore(scoreDto.getExameName(), scoreDto.getScore());
//            studentRepository.save(student);
            student.getScores().put(scoreDto.getExameName(), scoreDto.getScore());
            return true;
        }
        return false;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .collect(Collectors.toList());
    }

    @Override
    public long getStudentsNamesQuantity(List<String> names) {
        return studentRepository.findAll().stream()
                .filter(e -> names.contains(e.getName()))
                .count();
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, int minScore) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) >= minScore)
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .collect(Collectors.toList());
    }
}
