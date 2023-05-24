package telran.java47.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    int id;
    String name;
    Map<String, Integer> scores;
}
