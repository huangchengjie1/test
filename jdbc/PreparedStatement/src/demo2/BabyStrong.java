package demo2;

/*
    真实对象，宝宝
 */

public class BabyStrong implements star {
    @Override
    public void sing(String song) {
        System.out.println("宝宝唱歌：" + song);

    }

    @Override
    public void dance(String name) {
        System.out.println("宝宝跳舞:" + name);
    }
}
