

import demo2.BabyStrong;
import demo2.star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 调用者
 */
public class Fans {

    public static void main(String[] args) {
        //创建真实对象
        star s1 = new BabyStrong();
        //调用真实对象的方法
        s1.sing("再见只是陌生人");
        s1.dance("广场舞");

        //动态生成代理对象
        star s2 = (star) Proxy.newProxyInstance(
                //参数1：与真实对象相同的类加载器
                s1.getClass().getClassLoader(),
                //参数2：代理类所有实现的接口
                new Class[]{star.class},
                //参数3：这是一个接口，我们需要实现这个接口，传入这个接口的实现类
                new InvocationHandler() {
                    @Override
                    //参数1：proxy，这就是代理对象，不建议在invoke方法中去调用proxy，不然会出现递归的调用
                    //参数2：method对象，每个被代理的方法，真实对象的方法对象。每个方法都会调用一次
                    //参数3：代理对象调用这个method方法时提供的参数
                    //返回值是方法的返回值
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                       /* //直接调用真实对象的方法
                        return method.invoke(s1, args);*/
                        //如果是唱歌的方法就进行修改
                        if (method.getName().equals("sing")) {
                            System.out.println("经理人说：不唱了");
                            return null;
                        }
                        else {
                            //否则其它的方法还是调用原来的方法
                            return method.invoke(s1, args);
                        }
                    }
                }
        );

            //调用代理对象的方法
        s2.sing("单身情歌");
        s2.dance("机械舞");

    }

}
