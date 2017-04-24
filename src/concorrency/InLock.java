package concorrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caoyx on 2017/4/24 0024.
 */
public class InLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public InLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {

        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + " 线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InLock r1 = new InLock(1);
        InLock r2 = new InLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(5000);
        t2.interrupt();
    }
}
