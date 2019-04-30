package jdbc;


import javax.xml.crypto.Data;
import java.sql.*;

/*
    查询学生表中的记录
 */
public class Demo5DQL {

    public static void main(String[] args) throws SQLException {
        //创建连接对象

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day18", "root", "123456");
            //创建语句对象
            Statement stem = conn.createStatement();
            //执行DQL语句 executeQuery()
            ResultSet rs = stem.executeQuery("select * from student");
            //得到结果集
            while (rs.next()) {
                //遍历输出每一条记录
                //通过列名来取
               /* int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean gender = rs.getBoolean("gender");
                Date birthday = rs.getDate("birthday");
                System.out.println("编号:" + id + ",姓名:" + name + ",性别:" + (gender?"男":"女") + ",生日:" + birthday);*/
                //通过列号来取
                //  int id = rs.getInt(1);
                String id = rs.getString(1);
                String name = rs.getString(2);
                //    int name = rs.getInt(2);
                boolean gender = rs.getBoolean(3);
                Date birthday = rs.getDate(4);
                System.out.println("编号：" + id + "，姓名：" + name + "，性别：" + (gender ? "男" : "女") + "，生日：" + birthday);

            }
            //关闭连接对象
            rs.close();
            stem.close();
            conn.close();
    }
}
