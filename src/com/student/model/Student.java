package com.student.model;

/**
 * @Description: Student
 * @ClassName: Student
 * 
 */
public class Student {

    private String sno;
    private String sname;
    private String sex;
    private int age;
    private String sdept;
    private String username;
    @Deprecated
    private String password;

    public Student(String sno) {
        this.sno = sno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Deprecated
    public String getPassword() {
        return password;
    }

    @Deprecated
    public void setPassword(String password) {
        this.password = password;
    }

}
