package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
    使用JDBC创建一张表
 */
public class Demo3DDL {
    public static void main(String[] args) {
        //声明
        Connection conn  = null;
        Statement stmt = null;

        try {
            //0.注册驱动可以省略
            Class.forName("com.mysql.jdbc.Driver");
            //1.创建连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day18","root","123456");
            //2.得到语句对象
            stmt = conn.createStatement();
            //执行DDL SQL语句建表
           boolean b = stmt.execute("create table student (id int PRIMARY KEY auto_increment,name varchar(20) not null, gender boolean,birthday date)");
            System.out.println("b");  //false没有结果集，执行失败会抛出异常
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //4.关闭语句对象和连接对象
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
    }
}
