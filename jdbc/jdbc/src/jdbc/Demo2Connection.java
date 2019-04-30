package jdbc;


/*
    创建一个连接对象
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Demo2Connection {
    public static void main(String[] args) throws SQLException {
        //使用用户名,密码,url得到连接对象
      Connection connl =  DriverManager.getConnection("jdbc:mysql://localhost:3306/day18","root","123456");
        System.out.println("连接对象:" + connl);
        //创建属性集合
        Properties info = new Properties();
        //将用户名和密码放在属性集合中
        info.setProperty("user","root");
        info.setProperty("password","123456");
        //使用属性文件和url得到连接对象
        Connection conn2 = DriverManager.getConnection("jdbc:mysql:///day18",info);
        System.out.println("连接对象2:" + conn2);

    }




}
