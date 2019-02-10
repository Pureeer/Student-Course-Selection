package com.student.base;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.student.util.DBUtil;

/**
 * @Description: Data Access Base Object
 * @ClassName: BaseDAO
 * 
 */
public abstract class BaseDAO {

    protected final DBUtil db = DBUtil.getDBUtil();

    protected ResultSet rs;

    protected BaseDAO() {

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
     * @Description: query a student by sno.
     */
    public String[][] queryStudent(String sno) {
        String sql = "select * from student where sno=?";
        String[] param = {sno};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }

    /**
     *
     * @Description: query a student by username.
     */
    public String[][] queryUser(String sno) {
        String sql = "select * from student where username=?";
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
     * @Description: encrypt the password with SHA256
     */
    public String getSHA256(String password) {
        MessageDigest md;
        String ret = "";
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            ret = byte2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 
     * @Description: byte to Hexadecimal
     */
    private String byte2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < bytes.length; i++) {
            tmp = Integer.toHexString(bytes[i] & 0xFF);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
        }
        return sb.toString();
    }



    public class StudentExistException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public class UserExistException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public class StudentNotFoundException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public class StudentSelectedCourseException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public class CourseExistException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public class CourseSelectedException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public class CourseNotFoundException extends Exception {

        private static final long serialVersionUID = 1L;
    }

    public class CourseNotSelectedException extends Exception {

        private static final long serialVersionUID = 1L;
    }
}
