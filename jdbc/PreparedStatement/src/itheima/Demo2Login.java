package itheima;

/*
    实现用户的登录操作
 */

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo2Login {

    public static void main(String[] args) throws SQLException {
        //用户输入用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();

        //1.创建连接对象
        Connection conn = JdbcUtils.genConnection();

        //2.创建预编译语句对象
        PreparedStatement ps = conn.prepareStatement("select * from user where name = ? and password =?");

        //3.替换占位符,name和password
        ps.setString(1,name);
        ps.setString(2,password);

        //4.执行查询操作
       ResultSet rs = ps.executeQuery();

       //5.判断结果集是否有记录
        if (rs.next()){
            System.out.println("登录成功:" + name);
        } else {
            System.out.println("登录失败");
        }
        //6.关闭连接
        JdbcUtils.close(conn,ps,rs);

    }
}
