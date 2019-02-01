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
    
    public boolean queryForLogin (String username, String password) {
        boolean result = false;
        String sql = "select * from student where username=? and password=?";
        String[] param = {username, password};
        rs = db.executeQuery(sql, param);
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destory();
        }
        return result;
    }
    
    // TODO: Data Access of Student.
}
