package com.exam.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {

    private int id;
    private String name;
    private String email;
    private String course;
    private double programmingScore;
    private double mathScore;
    private List<String> skills;

    public Student() {
        this.skills = new ArrayList<>();
    }

    public Student(int id, String name, String email, String course,
                   double programmingScore, double mathScore, List<String> skills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.programmingScore = programmingScore;
        this.mathScore = mathScore;
        setSkills(skills);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(double programmingScore) {
        this.programmingScore = programmingScore;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        if (skills == null) {
            this.skills = new ArrayList<>();
        } else {
            this.skills = skills;
        }
    }

    public double getAverage() {
        return (programmingScore + mathScore) / 2;
    }

    public String getCategory() {
        double average = getAverage();
        if (average >= 85) {
            return "Excellent";
        } else if (average >= 70) {
            return "Good";
        } else if (average >= 50) {
            return "Satisfactory";
        } else {
            return "Needs Improvement";
        }
    }

    public String getStrongerSubject() {
        if (programmingScore > mathScore) {
            return "Programming";
        } else if (mathScore > programmingScore) {
            return "Mathematics";
        } else {
            return "Both Equal";
        }
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.getAverage(), this.getAverage());
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + name + ", email=" + email
            + ", course=" + course + ", programmingScore=" + programmingScore
            + ", mathScore=" + mathScore + ", skills=" + skills + "}";
    }
}
