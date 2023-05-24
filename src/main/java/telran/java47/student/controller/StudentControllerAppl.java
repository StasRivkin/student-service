package telran.java47.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.java47.student.model.Student;
import telran.java47.student.service.StudentService;
import telran.java47.student.dto.ScoreDto;
import telran.java47.student.dto.StudentCreateDto;
import telran.java47.student.dto.StudentDto;
import telran.java47.student.dto.StudentUpdateDto;

import java.util.List;

@RestController
public class StudentControllerAppl {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public boolean addStudent(@RequestBody StudentCreateDto studentCreateDto) {
        return studentService.addStudent(studentCreateDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable int id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(@PathVariable int id) {
        return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentCreateDto updateStudent(@PathVariable int id, @RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PutMapping("/score/student/{id}")
    public boolean addScore(@PathVariable int id, @RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudentsByName(@PathVariable String name) {
        return studentService.findStudentsByName(name);
    }

    @PostMapping("/quantity/students")
    public long getStudentsNamesQuantity(@RequestBody List<String> names) {
        return studentService.getStudentsNamesQuantity(names);
    }

    @GetMapping("/students/exam/{exam}/minscore/{minScore}")
    public List<StudentDto> getStudentsByExamMinScore(@PathVariable String exam, @PathVariable int minScore) {
        return studentService.getStudentsByExamMinScore(exam, minScore);
    }
}
