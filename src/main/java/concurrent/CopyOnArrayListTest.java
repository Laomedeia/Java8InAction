package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 底层采用了读写分离的 List，可用在多线程并发时候使用
 * 应注意设置合适的容器初始值和采用批量插入的方式
 * @author neptune
 * @create 2019 06 02 10:48
 */
public class CopyOnArrayListTest {

    public static void main(String[] args) {
        List<Long> copy = new CopyOnWriteArrayList<>();
        long start = System.currentTimeMillis();
        singleLoopInsert(copy);
//        batchInsert(copy);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void singleLoopInsert (List copy) {
        for (int i = 0; i < 20 * 10000; i++) {
            // CopyOnWriteArrayList 适合批量插入，不适合单个插入很耗性能
            copy.add(System.nanoTime());
        }
    }

    private static void batchInsert (List copy) {
        List<Long> tempList = new ArrayList<>();
        for (int i = 0; i < 20 * 10000; i++) {
            // CopyOnWriteArrayList 适合批量插入，不适合单个插入很耗性能
            tempList.add(System.nanoTime());
        }
        copy.addAll(tempList);
    }
}
