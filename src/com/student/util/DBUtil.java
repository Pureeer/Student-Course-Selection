package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.student.AppConstants;

/**
 * @Description: DataBase Utility
 * @ClassName: DBUtil
 * 
 */

public class DBUtil {

    private static DBUtil db;
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public static DBUtil getDBUtil() {
        if (db == null) {
            db = new DBUtil();
        }
        return db;
    }
    
    private DBUtil() {

    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet excuteQuery(String sql) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet executeQuery(String sql, Object[] obj) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
    
    public int executeUpdate(String sql) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(AppConstants.JDBC_DRIVER);
                conn = DriverManager.getConnection(AppConstants.JDBC_URL,
                        AppConstants.JDBC_USERNAME, AppConstants.JDBC_PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver is not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
