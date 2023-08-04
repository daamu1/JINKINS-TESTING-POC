package com.jinkin.service;

import java.util.ArrayList;
import java.util.List;

import com.jinkin.entity.Student;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student(1L, "John Doe", 20));
        students.add(new Student(2L, "Jane Smith", 22));
        students.add(new Student(3L, "Michael Johnson", 19));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Student addStudent(Student student) {
        student.setId(generateNewId());
        students.add(student);
        return student;
    }

    private Long generateNewId() {
        return students.stream()
                .mapToLong(Student::getId)
                .max()
                .orElse(0L) + 1L;
    }
}
