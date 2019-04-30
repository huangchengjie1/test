package utils;

import java.sql.*;

/*
    创建工具类
 */
public class JdbcUtils {

    //注册驱动
    static {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    //得到连接对象方法
    public static Connection genConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/day18","root","123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭连接方法
    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn,Statement stet){
        close(conn,stet,null);
    }
}
