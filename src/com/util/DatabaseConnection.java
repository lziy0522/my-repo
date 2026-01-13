package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 1. 数据库连接信息（**必须替换为你的MySQL信息**）
    // URL格式：jdbc:mysql://主机地址:端口号/数据库名?参数
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // 你的MySQL用户名
    private static final String PASSWORD = "123456"; // 你的MySQL密码

    // 2. 获取数据库连接的静态方法（直接调用，无需创建对象）
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 加载MySQL驱动（8.0以上版本用这个类，旧版本是com.mysql.jdbc.Driver）
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败！请检查驱动是否导入正确");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！请检查账号密码、数据库名是否正确");
            e.printStackTrace();
        }
        return conn;
    }

    // 3. 关闭连接的方法（释放资源，避免内存泄漏）
    public static void closeConnection(Connection conn) {
        if (conn != null) { // 避免空指针异常
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}