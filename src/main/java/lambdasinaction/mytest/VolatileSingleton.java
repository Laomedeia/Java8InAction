package lambdasinaction.mytest;

/**
 * 双重懒加载的单例模式
 * 这里的 volatile 关键字主要是为了防止指令重排。
    如果不用 ，singleton = new Singleton();，这段代码其实是分为三步：
    分配内存空间。(1)
    初始化对象。(2)
    将 singleton 对象指向分配的内存地址。(3)
    加上 volatile 是为了让以上的三步操作顺序执行，反之有可能第二步在第三步之前被执行就有可能某个线程拿到的单例对象是还没有初始化的，以致于报错。
 * @author neptune
 * @create 2018 03 12 下午11:04
 */
public class VolatileSingleton {
    private static volatile VolatileSingleton singleton;
    private VolatileSingleton() {
    }
    public static VolatileSingleton getInstance() {
        if (singleton == null) {
            synchronized (VolatileSingleton.class) {
                if (singleton == null) {
                    //防止指令重排
                    singleton = new VolatileSingleton();
                }
            }
        }
        return singleton;
    }
}
