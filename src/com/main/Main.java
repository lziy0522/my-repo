package com.main;

import com.entity.Student;
import com.manager.StudentManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. 创建业务类对象和扫描器对象
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        // 2. 循环显示菜单，直到用户选择退出
        while (true) {
            System.out.println("=================================");
            System.out.println("        学生管理系统 V1.0        ");
            System.out.println("  1. 添加学生信息                ");
            System.out.println("  2. 根据ID查询学生              ");
            System.out.println("  3. 显示所有学生信息            ");
            System.out.println("  4. 计算学生平均分              ");
            System.out.println("  5. 退出系统                    ");
            System.out.println("=================================");
            System.out.print("请输入你要执行的操作序号：");

            // 3. 获取用户输入的选项
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // 吸收换行符！！！解决后续nextLine()读取不到内容的问题
            } catch (Exception e) {
                scanner.nextLine(); // 清空错误输入
                System.out.println("输入错误！请输入数字1-5");
                continue; // 跳过本次循环，重新显示菜单
            }

            // 4. 根据用户选项执行对应功能
            switch (choice) {
                case 1:
                    // 添加学生
                    System.out.print("请输入学生姓名：");
                    String name = scanner.nextLine();
                    System.out.print("请输入学生性别（男/女）：");
                    String gender = scanner.nextLine();
                    System.out.print("请输入学生班级：");
                    String clazz = scanner.nextLine();
                    System.out.print("请输入高数成绩：");
                    double math = scanner.nextDouble();
                    System.out.print("请输入Java成绩：");
                    double java = scanner.nextDouble();

                    // 创建学生对象
                    Student student = new Student(name, gender, clazz, math, java);
                    // 调用添加方法
                    boolean isSuccess = manager.addStudent(student);
                    System.out.println(isSuccess ? "添加成功！" : "添加失败！");
                    break;

                case 2:
                    // 根据ID查询学生
                    System.out.print("请输入要查询的学生ID：");
                    int id = scanner.nextInt();
                    Student queryStudent = manager.queryStudentById(id);
                    if (queryStudent != null) {
                        System.out.println("===== 学生信息 =====");
                        System.out.println("学生ID：" + queryStudent.getId());
                        System.out.println("姓名：" + queryStudent.getName());
                        System.out.println("性别：" + queryStudent.getGender());
                        System.out.println("班级：" + queryStudent.getClazz());
                        System.out.println("高数成绩：" + queryStudent.getMathScore());
                        System.out.println("Java成绩：" + queryStudent.getJavaScore());
                    } else {
                        System.out.println("未找到ID为" + id + "的学生！");
                    }
                    break;

                case 3:
                    // 显示所有学生
                    List<Student> studentList = manager.showAllStudents();
                    if (studentList.isEmpty()) {
                        System.out.println("暂无学生信息！");
                    } else {
                        System.out.println("===== 所有学生信息 =====");
                        for (Student s : studentList) {
                            System.out.println("ID：" + s.getId() + " | 姓名：" + s.getName() + " | 性别：" + s.getGender() + " | 班级：" + s.getClazz() + " | 高数：" + s.getMathScore() + " | Java：" + s.getJavaScore());
                        }
                    }
                    break;

                case 4:
                    // 计算平均分
                    double avgScore = manager.calculateAverageScore();
                    // 保留2位小数
                    System.out.println("所有学生的平均分：" + String.format("%.2f", avgScore));
                    break;

                case 5:
                    // 退出系统
                    System.out.println("感谢使用学生管理系统，再见！");
                    scanner.close(); // 关闭扫描器
                    System.exit(0); // 退出程序
                    break;

                default:
                    System.out.println("输入错误！请输入数字1-5");
            }
            // 每次操作后换行，方便阅读
            System.out.println();
        }
    }
}