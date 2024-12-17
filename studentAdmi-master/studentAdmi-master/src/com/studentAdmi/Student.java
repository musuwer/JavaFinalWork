package com.studentAdmi;

/**
 * Student 类
 * 用于表示学生信息，包括姓名、性别、籍贯、学号、系别、班别等。
 */
public class Student {
    private String name;  // 姓名
    private String sex;   // 性别
    private String place; // 籍贯
    private String code;  // 学号
    private String dept;  // 系别
    private String ban;   // 班别

    // 默认构造方法
    public Student() {}

    /**
     * 带参数的构造方法，用于初始化学生对象
     *
     * @param name 姓名
     * @param sex 性别
     * @param place 籍贯
     * @param code 学号
     * @param dept 系别
     * @param ban 班别
     */
    public Student(String name, String sex, String place, String code, String dept, String ban) {
        this.name = name;
        this.sex = sex;
        this.place = place;
        this.code = code;
        this.dept = dept;
        this.ban = ban;
    }

    // 姓名的 getter 和 setter 方法
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    // 性别的 getter 和 setter 方法
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }

    // 籍贯的 getter 和 setter 方法
    public void setPlace(String place) {
        this.place = place;
    }
    public String getPlace() {
        return place;
    }

    // 学号的 getter 和 setter 方法
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    // 系别的 getter 和 setter 方法
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getDept() {
        return dept;
    }

    // 班别的 getter 和 setter 方法
    public void setBan(String ban) {
        this.ban = ban;
    }
    public String getBan() {
        return ban;
    }

    /**
     * 重写 toString 方法，用于输出学生的详细信息。
     *
     * @return 学生信息的字符串表示
     */
    @Override
    public String toString() {
        // 返回格式化的学生信息字符串
        return "    " + this.name + "    " + this.sex + "    " + this.place + "    " + this.code + "    " + this.dept + "    " + this.ban ;
    }
}
