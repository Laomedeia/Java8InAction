package concurrent.ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author neptune
 * @create 2020 05 30 2:38 下午
 */
public class AddTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + ": Element " + i);
        }
    }
}
