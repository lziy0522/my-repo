package com.manager;

import com.entity.Student;
import com.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    // 功能1：添加学生信息到数据库
    public boolean addStudent(Student student) {
        // 1. 获取数据库连接
        Connection conn = DatabaseConnection.getConnection();
        // 2. SQL语句：?是占位符，防止SQL注入，比直接拼接字符串更安全
        String sql = "INSERT INTO students(name, gender, class, math_score, java_score) VALUES (?, ?, ?, ?, ?)";

        try {
            // 3. 创建PreparedStatement对象，执行SQL
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 4. 给占位符赋值（顺序对应SQL中的?，从1开始）
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClazz());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());

            // 5. 执行插入操作：executeUpdate()返回受影响的行数，>0表示插入成功
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("添加学生失败！请检查SQL语句或数据库连接");
            e.printStackTrace();
            return false;
        } finally {
            // 6. 无论是否成功，都要关闭连接，释放资源
            DatabaseConnection.closeConnection(conn);
        }
    }

    // 功能2：根据学生ID查询单个学生信息
    public Student queryStudentById(int id) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null; // 初始化学生对象为null

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id); // 给占位符赋值
            // 执行查询操作：executeQuery()返回结果集ResultSet
            ResultSet rs = pstmt.executeQuery();

            // 遍历结果集（查询单个学生，用if即可）
            if (rs.next()) {
                student = new Student(); // 实例化学生对象
                // 从结果集中获取数据，设置到学生对象中
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClazz(rs.getString("class"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败！请检查学生ID是否存在");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return student;
    }

    // 功能3：查询所有学生信息，返回List集合
    public List<Student> showAllStudents() {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM students";
        List<Student> studentList = new ArrayList<>(); // 存储所有学生的集合

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // 遍历结果集（多个学生，用while循环）
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClazz(rs.getString("class"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                studentList.add(student); // 把学生对象添加到集合中
            }
        } catch (SQLException e) {
            System.out.println("查询所有学生失败！");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return studentList;
    }

    // 功能4：计算所有学生的平均分（(高数+Java)/2 的平均值）
    public double calculateAverageScore() {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT AVG((math_score + java_score)/2) AS avg_score FROM students";
        double avgScore = 0.0;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 获取计算后的平均分，列名对应SQL中的AS别名
                avgScore = rs.getDouble("avg_score");
            }
        } catch (SQLException e) {
            System.out.println("计算平均分失败！");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return avgScore;
    }
}