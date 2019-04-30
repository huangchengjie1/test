package itheima;

import javabean.Student;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    查询所以的学生对象,封装成List
 */
public class Demo3DQL {
    public static void main(String[] args) throws SQLException {
        //创建集合
        List<Student> students = new ArrayList<>();
        //创建连接对象
        Connection connection = JdbcUtils.genConnection();
        //得到预编译的语句对象
        PreparedStatement ps = connection.prepareStatement("select * from student");
        //查询数据得到结果集
        ResultSet rs = ps.executeQuery();
        //遍历每条记录,每条记录封装成一个学生对象
        while (rs.next()) {
            Student student = new Student();
            //封装每个属性
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setGender(rs.getBoolean("gender"));
            student.setBirthday(rs.getDate("birthday"));
            //封装好后添加到集合中
            students.add(student);
        }

        //关闭连接
        JdbcUtils.close(connection,ps,rs);

        //使用集合对象中的数据
        for (Student s : students){
            System.out.println(s);
        }
    }

}
