package com.example.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Integer studentId;

    @NotBlank
    private String studentName;

    @Email
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String courseName;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private double programmingScore;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private double mathematicsScore;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "student_skills",
        joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "skill")
    private List<String> technicalSkills = new ArrayList<>();

    public Student() {}

    // Getters and setters

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(double programmingScore) {
        this.programmingScore = programmingScore;
    }

    public double getMathematicsScore() {
        return mathematicsScore;
    }

    public void setMathematicsScore(double mathematicsScore) {
        this.mathematicsScore = mathematicsScore;
    }

    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    @JsonProperty("averageScore")
    @Transient
    public double getAverageScore() {
        return (programmingScore + mathematicsScore) / 2;
    }

    @JsonProperty("performanceCategory")
    @Transient
    public String getPerformanceCategory() {
        double avg = getAverageScore();

        if (avg >= 85) return "Excellent";
        else if (avg >= 70) return "Good";
        else if (avg >= 50) return "Satisfactory";
        else return "Needs Improvement";
    }

    @JsonProperty("strongerSubject")
    @Transient
    public String getStrongerSubject() {
        if (programmingScore > mathematicsScore)
            return "Programming";
        else if (mathematicsScore > programmingScore)
            return "Mathematics";
        else
            return "Both Equal";
    }
}