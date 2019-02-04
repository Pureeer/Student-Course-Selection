package com.student.dao;

import com.student.base.BaseDAO;

/**
 * @Description: Data Access Object of Student
 * @ClassName: AdminDAO
 * 
 */
public class AdminDAO extends BaseDAO {

    private static AdminDAO ad = null;

    public static synchronized AdminDAO getInstance() {
        if (ad == null) {
            ad = new AdminDAO();
        }
        return ad;
    }

    /**
     * 
     * @Description: query students who have selected a specific course "Grade Manager Window"
     */
    public String[][] queryStuWhoSeleCou(String cname) {
        String sql =
                "select sno,grade from course as A, stu_course as B where A.cno = B.cno and A.cname=?";
        String[] param = {cname};
        rs = db.executeQuery(sql, param);
        return buildResult();
    }
}
