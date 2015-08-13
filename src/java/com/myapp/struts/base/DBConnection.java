package com.myapp.struts.base;
//import com.mysql.jdbc.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection {
    static private String strDriver = "org.gjt.mm.mysql.Driver";
    //static private String strDriver = "com.mysql.jdbc.Driver";
    static private String strUrl = "jdbc:mysql://localhost:3306/login?characterEncoding=GBK";
    static private String strUser = "root";
    static private String strPwd = "1234";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    static {
        try {
            Class.forName(strDriver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error load" + strDriver);
        }
    }

    public DBConnection() {
    }

    public ResultSet executeQuery(String sql) {
        try {
            rs = getStatement().executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("query error:" + ex.getMessage());
        }
        return rs;
    }

    public boolean executeBatch(java.util.ArrayList sqls) {
        boolean bRet = false;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (int i = 0; i < sqls.size(); i++) {
                String sql = (String) sqls.get(i);
                stmt.addBatch(sql);
            }
            stmt.executeBatch();
            conn.commit();
            bRet = true;
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (Exception ex2) {
                System.err.println("rollback failed:" + ex2.getMessage());
            }
            ex.printStackTrace();
        } finally {
        }
        return bRet;
    }

    public int executeUpdate(String sql) {
        int resultNum = 0;
        try {
            resultNum = getStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("update error:" + ex.getMessage());
        } finally {
        }
        return resultNum;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            System.err.println("close error:" + ex.getMessage());
        }
    }

    private Connection getConnection() {
        try {
            if (conn == null || conn.isClosed())
                conn = DriverManager.getConnection(strUrl, strUser, strPwd);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return conn;
    }

    private Statement getStatement() {
        try {
            if (stmt == null)
                stmt = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return stmt;
    }
 }
