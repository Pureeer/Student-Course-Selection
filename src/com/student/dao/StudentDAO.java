package com.student.dao;

import java.sql.SQLException;
import com.student.base.BaseDAO;

/**
 * @Description: Data Access Object of Student
 * @ClassName: StudentDAO
 * 
 */
public class StudentDAO extends BaseDAO {

    private static StudentDAO sd = null;

    public static synchronized StudentDAO getInstance() {
        if (sd == null) {
            sd = new StudentDAO();
        }
        return sd;
    }

    public String queryForLogin(String username, String password) {
        String result = null;
        String sql = "select sno from student where binary username=? and binary password=?";
        String[] param = {username, password};
        rs = db.executeQuery(sql, param);
        try {
            if (rs.next()) {
                result = rs.getString("sno");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destory();
        }
        return result;
    }
    // TODO: Data Access of Student.

    public class CourseNotFoundException extends Exception {

        private static final long serialVersionUID = 1L;
    }

    public class CourseNotSelectException extends Exception {

        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     * @Description: query optional courses for a student.
     */
    public String[][] queryCourses(String sno) {
        String sql =
                "select * from course where cno not in (select cno from stu_course where sno=?)";
        String[] param = {sno};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }

    /**
     * 
     * @Description: query selected courses for a student.
     */
    public String[][] querySelectedCourse(String sno) {
        String sql =
                "select * from course where cno in (select cno from stu_course where sno=? and grade is null)";
        String[] param = {sno};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }

    /**
     * 
     * @throws CourseNotFoundException
     * @throws CourseNotSelectException
     * @Description: query a student's grade of a course.
     */
    public int queryCourseGrade(String sno, String cno)
            throws CourseNotFoundException, CourseNotSelectException {
        String[][] course = queryCourse(cno);
        if (course.length == 0) {
            throw new CourseNotFoundException();
        }
        String sql = "select grade from stu_course where sno=? and cno=?";
        String[] param = {sno, cno};
        rs = db.executeQuery(sql, param);
        String grade = null;
        try {
            if (rs.next()) {
                grade = rs.getString("grade");
            } else {
                throw new CourseNotSelectException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destory();
        }
        return Integer.parseInt(grade);
    }

    /**
     *
     * @Description: select course for a student.
     */
    public void selectCourse(String sno, String cno) {
        String sql = "insert into stu_course values (?,?,null)";
        String[] param = {sno, cno};
        db.executeUpdate(sql, param);
    }

    /**
     *
     * @Description: drop course for a student.
     */
    public void dropCourse(String sno, String cno) {
        String sql = "delete from stu_course where sno=? and cno=?";
        String[] param = {sno, cno};
        db.executeUpdate(sql, param);
    }
}
