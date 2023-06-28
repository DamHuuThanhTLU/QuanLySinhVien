package com.example.qlsinhvien.model;

public class Subject {
    //Các biến thông tin môn học

    //id
    private int id_subject;
    //tên môn học
    private String subject_title;
    //Số tín chỉ
    private int Number_of_credits;
    //Khoảng thời gian
    private String Time;
    //địa điểm
    private String place;

    public Subject(int id_subject, String subject_title, int number_of_credits, String time, String place) {
        this.id_subject = id_subject;
        this.subject_title = subject_title;
        Number_of_credits = number_of_credits;
        Time = time;
        this.place = place;
    }

    public Subject(String subject_title, int number_of_credits, String time, String place) {
        this.subject_title = subject_title;
        Number_of_credits = number_of_credits;
        Time = time;
        this.place = place;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public int getNumber_of_credits() {
        return Number_of_credits;
    }

    public void setNumber_of_credits(int number_of_credits) {
        Number_of_credits = number_of_credits;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
