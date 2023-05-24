package telran.java47.student.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode(of = "id")
public class Student {
    int id;
    @Setter
    String name;
    @Setter
    String password;
    Map<String, Integer> scores;

    public Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        scores = new HashMap<>();
    }

    public boolean addScore(String exam, Integer score) {
        return scores.put(exam, score) == null;
    }
}
