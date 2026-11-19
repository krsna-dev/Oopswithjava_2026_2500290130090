package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    // Constructor Injection
    public StudentController(StudentService service) {
        this.service = service;
    }

    // 1. Add student
    @PostMapping
    public ResponseEntity<Student> addStudent(
            @Valid @RequestBody Student student) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addStudent(student));
    }

    // 2. Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // 3. Search student by ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.getStudentById(id);
    }

    // 4. Update student
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @Valid @RequestBody Student student) {

        return service.updateStudent(id, student);
    }

    // 5. Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable int id) {

        service.deleteStudent(id);

        return ResponseEntity.ok(
                "Student deleted successfully");
    }

    // 6. Merit list
    @GetMapping("/merit-list")
    public List<Student> meritList() {
        return service.getMeritList();
    }

    // 7. Search by email
    @GetMapping("/search/email")
    public List<Student> searchByEmail(
            @RequestParam String email) {

        return service.searchByEmail(email);
    }

    // 8. Search by course
    @GetMapping("/search/course")
    public List<Student> searchByCourse(
            @RequestParam String course) {

        return service.searchByCourse(course);
    }

    // 9. Common technical skills
    @GetMapping("/common-skills")
    public Map<String, List<Student>> commonSkills() {
        return service.getCommonSkills();
    }

    // 10. Students needing programming improvement
    @GetMapping("/programming-improvement")
    public List<Student> programmingImprovement() {
        return service.getStudentsNeedingProgrammingImprovement();
    }
}
