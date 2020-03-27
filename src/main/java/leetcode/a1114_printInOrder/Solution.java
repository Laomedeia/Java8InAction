package leetcode.a1114_printInOrder;

import java.util.concurrent.CountDownLatch;

/**
 * @author neptune
 * @create 2020 03 26 11:45 上午
 */
class Solution {
    final CountDownLatch latch = new CountDownLatch(1);
    final CountDownLatch latch2 = new CountDownLatch(1);

    class ThreadA implements Runnable {
        @Override
        public void run() {
            System.out.println("first");
            latch.countDown();
        }
    }

    class ThreadB implements Runnable {
        @Override
        public void run() {
            try {
                latch.await();
                System.out.println("second");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch2.countDown();
            }
        }
    }

    class ThreadC implements Runnable {
        @Override
        public void run() {
            try {
                latch2.await();
                System.out.println("third");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ThreadA threadA;
    private ThreadB threadB;
    private ThreadC threadC;

    public Solution() {
        threadA = new ThreadA();
        threadB = new ThreadB();
        threadC = new ThreadC();
        try {
            first(threadA);
            second(threadB);
            third(threadC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}
