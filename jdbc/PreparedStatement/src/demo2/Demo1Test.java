package demo2;

import java.sql.Connection;
import java.sql.SQLException;

/*
    使用连接池
 */
public class Demo1Test {

    public static void main(String[] args) throws SQLException {
        //创建连接池
        MyDataSource ds = new MyDataSource();
        for (int i = 0; i < 11; i++){
            //从连接池中得到一个连接对象
           Connection conn = ds.genConnection();
            System.out.println("第" + (i + 1) + "个连接对象:" + conn);
            //假设第5个连接释放
            if (i == 5){
                conn.close(); //不能直接关闭
                //释放连接对象
                ds.releaseConnection(conn);
            }
        }
    }
}
