package com.example.qlsinhvien.model;

public class Student {
    private int id_student;
    private String student_name;
    private String sex;
    private String student_code;
    private String date_of_birth;
    private int id_subject;

    public Student(String student_name, String sex, String student_code, String date_of_birth) {
        this.student_name = student_name;
        this.sex = sex;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
    }

    public Student(String student_name, String sex, String student_code, String date_of_birth, int id_subject) {
        this.student_name = student_name;
        this.sex = sex;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.id_subject = id_subject;
    }

    public Student(int id_student, String student_name, String sex, String student_code, String date_of_birth, int id_subject) {
        this.id_student = id_student;
        this.student_name = student_name;
        this.sex = sex;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.id_subject = id_subject;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }



    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
