package demo2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo1TestUtils {

    public static void main(String[] args) throws SQLException {

        //创建集合对象
        List<Map<String,Object>> list = new ArrayList<>();

        //创建连接对象，通过连接池的工具类创建连接对象
        Connection conn = DataSourceUtils.getConnection();

        //创建预编译的语句对象
       PreparedStatement ps = conn.prepareStatement("select e.id,e.name,e.salary,e.join_date, d.name from emp e inner join dept d on e.dept_id=d.id order by id");

       //发送语句
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
        HashMap<String,Object> map = new HashMap<>();

        map.put("id",rs.getInt(1));
        map.put("name",rs.getString(2));
        map.put("salary",rs.getDouble(3));
        map.put("join_date",rs.getDate(4));
        map.put("dname",rs.getString(5));

        list.add(map);
        }

        //关闭连接
        DataSourceUtils.close(conn,ps,rs);


        //循环遍历输出
        for (Map<String,Object> map : list){
            System.out.println(map);
        }

    }

}
