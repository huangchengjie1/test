package jdbc;

import com.sun.jdi.connect.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
    在JDBC中使用事务
 */
public class Demo7Transection {
    public static void main(String[] args) {
        //得到连接对象
        Connection conn = null;
        Statement stmt = null;

        try {
        //得到连接对象
        conn = JdbcUtils.genConnection();
        //开启事务
         conn.setAutoCommit(false);
         //得到语句对象
         stmt = conn.createStatement();

         //jack扣钱,更新
            stmt.executeUpdate("update account set balance = balance- 500 where name = 'Java'");
         //模拟出现异常
           // System.out.println(100 / 0);
            //rose加钱，更新
            stmt.executeUpdate("update account set balance = balance + 500 where name = 'Rose'");

            //提交事务
            conn.commit();
            //如果没有异常输出转账成功
            System.out.println("转账成功");

        } catch (Exception e) {
            System.out.println("转账失败");
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            JdbcUtils.close(conn,stmt);
        }
    }
}
