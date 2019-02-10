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
     * @Description: query students who have selected a specific course "Grade
     *               Manager Window"
     */
    public String[][] queryStuWhoSeleCou(String cno) {
        String sql = "select sno,grade from course as A, stu_course as B where A.cno=B.cno and A.cno=?";
        String[] param = { cno };
        rs = db.executeQuery(sql, param);
        return buildResult();
    }

    public String[][] getAllCourse() {
        String sql = "select * from course";
        rs = db.executeQuery(sql);
        return buildResult();
    }

    public String[][] getAllStudents() {
        String sql = "select * from student";
        rs = db.executeQuery(sql);
        return buildResult();
    }

    /**
     * 
     * @Description: update a student's grade.
     */
    public int updateCourseGrade(String sno, String cno, String grade) {
        String sql = "update stu_course set grade=? where sno=? and cno=?";
        String[] prarm = { grade, sno, cno };
        return db.executeUpdate(sql, prarm);
    }

    public void AddCourse(String[] prarm) {
        String sql = "insert into course values(?, ? ,?, ? ,?)";
        db.executeUpdate(sql, prarm);
    }

    public void DelCourse(String cno) {
        String sql = "delete from course where cno = ?";
        String[] prarm = { cno };
        db.executeUpdate(sql, prarm);
    }

    public void AddStudent(String[] prarm) {
        String sql = "insert into student values(?,?,?,?,?,?,?)";
        prarm[6] = getSHA256(prarm[5] + prarm[6]);
        db.executeUpdate(sql, prarm);
    }

    public void DelStudent(String sno) {
        String sql = "delete from student where sno = ?";
        String[] prarm = { sno };
        db.executeUpdate(sql, prarm);
    }
}
