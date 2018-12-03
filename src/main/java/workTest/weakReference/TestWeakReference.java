package workTest.weakReference;

import java.lang.ref.WeakReference;

/**
 * java 弱引用例子
 * http://puretech.iteye.com/blog/2008663
 * https://www.jianshu.com/p/58628c04895f
 * @author neptune
 * @create 2018 11 30 6:09 PM
 */
public class TestWeakReference {

    public static void case1() {
        Car car = new Car(22000,"silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);
        int i=0;
        for(;;){
//            if(i==10000) {
//                car = null;
//            }
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }

    public static void case2() {
        WeakReference<String> weak = new WeakReference<String>(new String("hello"));
        System.out.println("before gc, weak.get() = " + weak.get());

        // 第11行在这！！！ 声明一个强引用也指向String对象. 可注释掉对比效果
//        String strong = weak.get();

        System.gc();// 通知gc进行垃圾回收，但不一定会立即响应，所以让主线程睡眠3秒等待gc线程执行
        try {
            Thread.sleep(3000);//
        }catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("after gc, weak.get() = " + weak.get());
    }


    public static void main(String[] args) throws InterruptedException {
//        TestWeakReference.case1();
        TestWeakReference.case2();
    }
}
