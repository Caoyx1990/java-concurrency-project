package concorrency;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caoyx on 2017/4/25 0025.
 */
public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition rlc = new ReenterLockCondition();
        Thread t1 = new Thread(rlc);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
