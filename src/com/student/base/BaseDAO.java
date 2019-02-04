package com.student.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.student.DAO;
import com.student.dao.AdminDAO;
import com.student.dao.StudentDAO;
import com.student.util.DBUtil;

/**
 * @Description: Data Access Base Object
 * @ClassName: BaseDAO
 * 
 */
public abstract class BaseDAO {

    private static BaseDAO baseDAO;

    public static synchronized BaseDAO getAbilityDAO(DAO dao) {
        switch (dao) {
            case AdminDAO:
                if (baseDAO == null || baseDAO.getClass() != AdminDAO.class) {
                    baseDAO = AdminDAO.getInstance();
                }
                break;
            case StudentDAO:
                if (baseDAO == null || baseDAO.getClass() != StudentDAO.class) {
                    baseDAO = StudentDAO.getInstance();
                }
                break;
            default:
                break;
        }
        return baseDAO;
    }

    protected final DBUtil db = DBUtil.getDBUtil();

    protected ResultSet rs;

    public BaseDAO() {

    }

    protected void destory() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Description: buildResult build the query result to array.
     */
    protected String[][] buildResult() {
        Vector<String[]> table = new Vector<String[]>();
        int columcount = 0;
        try {
            columcount = rs.getMetaData().getColumnCount();
            String[] data;
            while (rs.next()) {
                data = new String[columcount];
                for (int i = 0; i < columcount; i++) {
                    data[i] = rs.getString(i + 1);
                }
                table.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destory();
        }
        return table.toArray(new String[table.size()][columcount]);
    }

    /**
     *
     * @Description: queryStudent query a student by sno.
     */
    public String[][] queryStudent(String sno) {
        String sql = "select * from student where sno=?";
        String[] param = {sno};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }

    /**
     * 
     * @Description: query a course by cno.
     */
    public String[][] queryCourse(String cno) {
        String sql = "select * from course where cno=?";
        String[] param = {cno};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }

    /**
     * 
     * @Description: query the grade of a specific student.
     */
    public String[][] queryStuGrade(String sno) {
        String sql =
                "select A.cno, cname, grade from course as A, stu_course as B where A.cno = B.cno and sno=? and grade is not null";
        String[] param = {sno};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }
}
