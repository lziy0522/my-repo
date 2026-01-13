package com.entity;

// 学生实体类：属性 + 构造函数 + set/get方法
public class Student {
    // 字段对应数据库表（id是自增主键，不用手动设置）
    private Integer id;
    private String name;
    private String gender;
    private String clazz; // 注意：不能用class，因为class是Java关键字
    private Double mathScore;
    private Double javaScore;

    // 1. 无参构造函数（必须有，后续反射会用到）
    public Student() {
    }

    // 2. 有参构造函数（用于快速创建对象，不含id）
    public Student(String name, String gender, String clazz, Double mathScore, Double javaScore) {
        this.name = name;
        this.gender = gender;
        this.clazz = clazz;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
    }

    // 3. set/get方法（用于设置和获取属性值）
    // ********** 以下代码可通过快捷键自动生成 **********
    // 快捷键：Alt + Insert → 选择 Getter and Setter → 全选字段 → 确定
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Double getMathScore() {
        return mathScore;
    }

    public void setMathScore(Double mathScore) {
        this.mathScore = mathScore;
    }

    public Double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(Double javaScore) {
        this.javaScore = javaScore;
    }
}