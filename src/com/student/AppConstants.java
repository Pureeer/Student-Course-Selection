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
}
