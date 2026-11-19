package com.exam.demo.repository;

import com.exam.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "NewPass123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean existsById(int id) throws SQLException {

        String sql = "SELECT student_id FROM student WHERE student_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public void addStudent(Student student) throws SQLException {

        String sql = "INSERT INTO student "
            + "(student_id, name, email, course, programming_score, math_score) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());
            ps.setDouble(5, student.getProgrammingScore());
            ps.setDouble(6, student.getMathScore());
            ps.executeUpdate();

            saveSkills(con, student.getId(), student.getSkills());
        }
    }

    public List<Student> getAllStudents() throws SQLException {

        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student ORDER BY student_id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(makeStudent(con, rs));
            }
        }
        return students;
    }

    public Student getStudentById(int id) throws SQLException {

        String sql = "SELECT * FROM student WHERE student_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return makeStudent(con, rs);
            }
        }
        return null;
    }

    public Student getStudentByEmail(String email) throws SQLException {

        String sql = "SELECT * FROM student WHERE email = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return makeStudent(con, rs);
            }
        }
        return null;
    }

    public List<Student> getStudentsByCourse(String course) throws SQLException {

        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE course = ? ORDER BY student_id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(makeStudent(con, rs));
            }
        }
        return students;
    }

    public boolean updateStudent(int id, Student student) throws SQLException {

        String sql = "UPDATE student "
            + "SET programming_score = ?, math_score = ? "
            + "WHERE student_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, student.getProgrammingScore());
            ps.setDouble(2, student.getMathScore());
            ps.setInt(3, id);

            if (ps.executeUpdate() == 0) {
                return false;
            }

            deleteSkills(con, id);
            saveSkills(con, id, student.getSkills());
            return true;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {

        String sql = "DELETE FROM student WHERE student_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private Student makeStudent(Connection con, ResultSet rs) throws SQLException {

        int id = rs.getInt("student_id");

        return new Student(
            id,
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("course"),
            rs.getDouble("programming_score"),
            rs.getDouble("math_score"),
            getSkills(con, id)
        );
    }

    private List<String> getSkills(Connection con, int id) throws SQLException {

        List<String> skills = new ArrayList<>();
        String sql = "SELECT skill FROM student_skill WHERE student_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skills.add(rs.getString("skill"));
            }
        }
        return skills;
    }

    private void saveSkills(Connection con, int id, List<String> skills) throws SQLException {

        String sql = "INSERT INTO student_skill (student_id, skill) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            for (String skill : skills) {
                ps.setInt(1, id);
                ps.setString(2, skill.trim());
                ps.executeUpdate();
            }
        }
    }

    private void deleteSkills(Connection con, int id) throws SQLException {

        String sql = "DELETE FROM student_skill WHERE student_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
