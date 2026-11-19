package com.exam.demo.service;

import com.exam.demo.model.Student;
import com.exam.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean isValidScore(double score) {
        return score >= 0 && score <= 100;
    }

    public boolean studentExists(int id) throws SQLException {
        return studentRepository.existsById(id);
    }

    public void addStudent(Student student) throws SQLException {
        studentRepository.addStudent(student);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(int id) throws SQLException {
        return studentRepository.getStudentById(id);
    }

    public Student getStudentByEmail(String email) throws SQLException {
        return studentRepository.getStudentByEmail(email);
    }

    public List<Student> getStudentsByCourse(String course) throws SQLException {
        return studentRepository.getStudentsByCourse(course);
    }

    public boolean updateStudent(int id, Student student) throws SQLException {
        return studentRepository.updateStudent(id, student);
    }

    public boolean deleteStudent(int id) throws SQLException {
        return studentRepository.deleteStudent(id);
    }

    public List<Student> getMeritList() throws SQLException {
        List<Student> students = studentRepository.getAllStudents();
        Collections.sort(students);
        return students;
    }

    public Map<String, List<String>> getSameSkills() throws SQLException {

        List<Student> students = studentRepository.getAllStudents();
        Map<String, List<String>> skillMap = new HashMap<>();

        for (Student student : students) {
            for (String skill : student.getSkills()) {
                String key = skill.trim().toLowerCase();
                if (!skillMap.containsKey(key)) {
                    skillMap.put(key, new ArrayList<>());
                }
                if (!skillMap.get(key).contains(student.getName())) {
                    skillMap.get(key).add(student.getName());
                }
            }
        }

        Map<String, List<String>> sameSkills = new TreeMap<>();
        for (String skill : skillMap.keySet()) {
            if (skillMap.get(skill).size() > 1) {
                sameSkills.put(skill, skillMap.get(skill));
            }
        }
        return sameSkills;
    }

    public List<Student> getNeedsImprovementInProgramming() throws SQLException {

        List<Student> students = studentRepository.getAllStudents();
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getProgrammingScore() < 50 && student.getMathScore() >= 75) {
                result.add(student);
            }
        }
        return result;
    }
}
