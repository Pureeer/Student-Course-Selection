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
        String sql = "select sno from student where username=? and password=?";
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
}
