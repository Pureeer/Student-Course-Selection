package com.student.base;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
