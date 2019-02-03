package com.student.dao;

import java.sql.SQLException;
import java.util.Vector;
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
     * @Description: queryStudent query a student by "sno".
     */
    public String[][] queryStudent(String sno) {
        String sql = "select * from student where sno=?";
        String[] param = {sno};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }

    /**
     *
     * @Description: buildResult build the query result to array.
     */
    private String[][] buildResult() {
        Vector<String[]> table = new Vector<String[]>();
        int columcount = 0;
        try {
            columcount = rs.getMetaData().getColumnCount();
            String[] data = new String[columcount];
            while (rs.next()) {
                for (int i = 0; i < columcount; i++) {
                    data[i] = rs.getString(i + 1);
                }
                table.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table.toArray(new String[table.size()][columcount]);
    }
}
