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
    public static final String STUDENT_INPUT = "请输入课程号";

    public static final String STUDENT_INFO = "学生详细信息";
    public static final String STUDENT_COURSE = "可选课程";
    public static final String STUDENT_SCORE = "已修课程成绩";
    public static final String STUDENT_SELECTED = "已选课程";

    public static final String SNO = "学号";
    public static final String SNAME = "姓名";
    public static final String SEX = "性别";
    public static final String AGE = "年龄";
    public static final String SDEPT = "所在院系";
    public static final String USERNAME = "用户名";
    public static final String PASSWORD = "密码";

    public static final String CNO = "课程号";
    public static final String CNAME = "课程名";
    public static final String CREDIT = "学分";
    public static final String CDEPT = "开课院系";
    public static final String TNAME = "任课老师";

    public static final String SCORE = "成绩";

    public static final String CNO_NULL_ERROR = "请输入课程号";
    public static final String CNO_NOT_EXIST_ERROR = "课程号不存在";
    public static final String CNO_SELECTED_ERROR = "此课程已选";
    public static final String CNO_NOT_SELECTED_ERROR = "此课程未选";
    public static final String CNO_GRADED_ERROR = "此课程已登分";

    public static final String VERIFY = "确认";
    public static final String DELETE = "删除";
    public static final String TOTAL_COUNT = "记录总数:";
    // Admin
    public static final String ADMIN_TITLE = "成绩管理";
    public static final String ADMIN_QUERY = "查询";
    public static final String ADMIN_INPUT = "登分";
    public static final String ADMIN_SAVE = "保存";
    public static final String ADMIN_CLOSE = "关闭";
    public static final String ADMIN_CHOOSE = "请选择课程名：";
    public static final String ADMIN_SELECTIONINFO = "选修此课程的学生：";

    public static final String ADMIN_CNAME = "课程：";
    public static final String ADMIN_TNAME = "任课教师：";

    public static final String ADMIN_MAINTAIN = "维护";
    public static final String ADMIN_COURSEINFO = "课程信息";
    public static final String ADMIN_STUDENTINFO = "学生信息";

    public static final String ADMIN_COURSEINFO_ADD = "新增课程";
    public static final String ADMIN_COURSEINFO_DEL = "删除课程";
    public static final String ADMIN_COURSEINFO_QUIT = "退出";

    public static final String ADMIN_SUTDENTINFO = "学生信息";
    public static final String ADMIN_SUTDENTINFO_ADD = "添加学生";
    public static final String ADMIN_SUTDENTINFO_DEL = "删除学生";

}
