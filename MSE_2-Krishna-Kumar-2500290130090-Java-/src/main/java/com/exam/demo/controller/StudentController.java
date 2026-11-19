package com.exam.demo.controller;

import com.exam.demo.model.Student;
import com.exam.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Object> addStudent(@RequestBody Student student) throws SQLException {

        if (!studentService.isValidScore(student.getProgrammingScore())
            || !studentService.isValidScore(student.getMathScore())) {
            return new ResponseEntity<>("Scores must be between 0 and 100", HttpStatus.BAD_REQUEST);
        }

        if (studentService.studentExists(student.getId())) {
            return new ResponseEntity<>("Student ID already exists", HttpStatus.BAD_REQUEST);
        }

        studentService.addStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws SQLException {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable int id) throws SQLException {

        Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<Object> searchByEmail(@RequestParam String email) throws SQLException {

        Student student = studentService.getStudentByEmail(email);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<Object> searchByCourse(@RequestParam String course) throws SQLException {

        List<Student> students = studentService.getStudentsByCourse(course);
        if (students.isEmpty()) {
            return new ResponseEntity<>("No student found in this course", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable int id,
                                                @RequestBody Student student) throws SQLException {

        if (!studentService.isValidScore(student.getProgrammingScore())
            || !studentService.isValidScore(student.getMathScore())) {
            return new ResponseEntity<>("Scores must be between 0 and 100", HttpStatus.BAD_REQUEST);
        }

        if (!studentService.updateStudent(id, student)) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) throws SQLException {

        if (!studentService.deleteStudent(id)) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

    @GetMapping("/merit")
    public ResponseEntity<List<Student>> getMeritList() throws SQLException {
        return new ResponseEntity<>(studentService.getMeritList(), HttpStatus.OK);
    }

    @GetMapping("/same-skills")
    public ResponseEntity<Map<String, List<String>>> getSameSkills() throws SQLException {
        return new ResponseEntity<>(studentService.getSameSkills(), HttpStatus.OK);
    }

    @GetMapping("/needs-improvement")
    public ResponseEntity<List<Student>> getNeedsImprovementInProgramming() throws SQLException {
        return new ResponseEntity<>(studentService.getNeedsImprovementInProgramming(), HttpStatus.OK);
    }
}
