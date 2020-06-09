package concurrent.ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author neptune
 * @create 2020 05 30 2:39 下午
 */
public class RemoveTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public RemoveTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
