package demo2;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/*
    自己创建的连接池
 */
public class MyDataSource implements DataSource {

    //连接池的容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    //初始连接数
    int initPoolSize = 5;
    //最大连接数
    int maxPoolSize = 10;
    //记录当前有多少个连接
    int currSize = 0;

    /*
        构造方法一开始创建一定数量的连接
     */
    public MyDataSource(){
       for (int i = 0; i < initPoolSize; i++){
           try {
               Connection conn = createConnection();
               //将创建好的连接对象放在连接池中
               pool.addLast(conn);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }
    //写一个私有的方法,创建一个连接对象
    private Connection createConnection() throws SQLException {
        //每创建一个,计数加1
        currSize++;
       Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/day19","root","123456");

       //得到代理对象
        Connection proxy = (Connection) Proxy.newProxyInstance(
                //参数1：与真实对象相同的类加载器
                conn.getClass().getClassLoader(),
                //参数2：代理对象所有实现的接口
                new Class[]{Connection.class},
                //要实现的接口
                new InvocationHandler() {
                    //参数1：代理对象
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //如果是close()方法则改写，将连接对象放回到连接池中
                        if (method.getName().equals("close")) {
                            //将代理对象放回到连接池中
                            pool.addLast((Connection) proxy);
                            return null;
                        }
                        else {
                            //其它的方法不要修改，还是调用真实对象的方法
                            return method.invoke(conn, args);
                        }
                    }
                }
        );
        return proxy;
    }

    /*
        关闭连接对象
        参数:要关闭的连接对象
     */
    public void releaseConnection(Connection conn){
        pool.addLast(conn);
    }

    /*
        用户调用这个方法得到连接对象
     */
    @Override
    public Connection genConnection() throws SQLException {
        //1.如果连接池有连接对象,从池中拿一个对象给用户
        if (pool.size() > 0){
            return pool.removeFirst();
        }
        //2.如果连接池中没有连接对象,但没有到达最大连接数,创建一个新的给用户
        else if (pool.size() == 0 && currSize < maxPoolSize) {
            return createConnection();  //再创建一个
        }
        else {
            //3.连接池中没有对象,而且已经到达最大连接数,抛出异常
            throw new SQLException("已经到达" + maxPoolSize + "最大连接数");
        }
    }
}
