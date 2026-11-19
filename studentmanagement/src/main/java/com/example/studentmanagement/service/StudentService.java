package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Add student
    public Student addStudent(Student student) {

        if (repository.existsById(student.getStudentId())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Student ID already exists");
        }

        return repository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Get student by ID
    public Student getStudentById(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found"));
    }

    // Update student
    public Student updateStudent(int id, Student student) {

        Student old = getStudentById(id);

        old.setStudentName(student.getStudentName());
        old.setEmailAddress(student.getEmailAddress());
        old.setCourseName(student.getCourseName());
        old.setProgrammingScore(student.getProgrammingScore());
        old.setMathematicsScore(student.getMathematicsScore());
        old.setTechnicalSkills(student.getTechnicalSkills());

        return repository.save(old);
    }

    // Delete student
    public void deleteStudent(int id) {
        getStudentById(id);
        repository.deleteById(id);
    }

    // Merit list
    public List<Student> getMeritList() {

        List<Student> students = repository.findAll();

        students.sort((a, b) ->
            Double.compare(
                b.getAverageScore(),
                a.getAverageScore()
            )
        );

        return students;
    }

    // Search by email
    public List<Student> searchByEmail(String email) {
        return repository.findByEmailAddress(email);
    }

    // Search by course
    public List<Student> searchByCourse(String course) {
        return repository.findByCourseName(course);
    }

    // Common skills
    public Map<String, List<Student>> getCommonSkills() {

        Map<String, List<Student>> result = new HashMap<>();

        List<Student> students = repository.findAll();

        for (Student student : students) {
            for (String skill : student.getTechnicalSkills()) {

                result.putIfAbsent(skill, new ArrayList<>());

                result.get(skill).add(student);
            }
        }

        return result;
    }

    // Programming improvement
    public List<Student> getStudentsNeedingProgrammingImprovement() {

        List<Student> result = new ArrayList<>();

        for (Student student : repository.findAll()) {

            if (student.getProgrammingScore() < 50 &&
                student.getMathematicsScore() >= 75) {

                result.add(student);
            }
        }

        return result;
    }
}