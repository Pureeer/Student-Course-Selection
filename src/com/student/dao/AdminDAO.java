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
    
    
}
