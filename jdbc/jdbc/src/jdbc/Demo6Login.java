package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo6Login {

    public static void main(String[] args) {
        //用户输入用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        //创建连接对象,通过工具类
        conn = JdbcUtils.genConnection();

        //创建语句对象
          stmt = conn.createStatement();

          //字符拼接
            String sql = "select * from user where name = '"+ name +"' and password = '"+ password +"'";

            System.out.println(sql);

            //查询得到结果集
            rs = stmt.executeQuery(sql);
            //判断结果集中是否有记录,如果有表示登录成功
            if (rs.next()){
                System.out.println("登录成功,欢迎你:" + name);
            } else {
                //如果没有,表示登录失败
                System.out.println("登录失败:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            JdbcUtils.close(conn,stmt,rs);
        }
    }



}
