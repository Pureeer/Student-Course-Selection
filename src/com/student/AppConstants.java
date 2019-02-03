package com.student;

/**
 * @Description: Constants
 * @ClassName: AppConstants
 * 
 */
public class AppConstants {

    // JDBC
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://119.29.176.110:3306/school";
    public static final String JDBC_USERNAME = "db1";
    public static final String JDBC_PASSWORD = "shuacm";

    // Login
    public static final String LOGIN_TITLE = "用户登录";
    public static final String LOGIN = "登录";
    public static final String EXIT = "退出";
    public static final String LOGIN_USERNAME = "用户名：";
    public static final String LOGIN_PASSWORD = "密    码：";
    public static final String LOGIN_ERROR = "用户名或密码错误";
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    public static final String REGEX_USERNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";

    // Student
    public static final String STUDENT_TITLE = "学生选课";
    public static final String STUDENT_SELECT = "选课";
    public static final String STUDENT_DROP = "退课";
    public static final String STUDENT_CLOSE = "关闭";

    public static final String STUDENT_INFO = "学生详细信息";
    public static final String SNO = "学号";
    public static final String SNAME = "姓名";
    public static final String SEX = "性别";
    public static final String AGE = "年龄";
    public static final String SDEPT = "所在院系";
    
    public static final String STUDENT_COURSE = "可选课程";
    public static final String CNO = "课程号";
    public static final String CNAME = "课程名";
    public static final String CREDT = "学分";
    public static final String TNAME = "任课老师";
    public static final String STUDENT_INPUT = "请输入课程号";
}
