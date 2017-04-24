package concorrency;

/**
 * Created by caoyx on 2017/4/17 0017.
 */
public class AccountingSync implements Runnable {
    static AccountingSync instance = new AccountingSync();
    static int i = 0;
//    @Override
//    public void run() {
//        for (int j = 0; j < 10000000; j ++) {
//            synchronized(instance) {
//                i ++;
//            }
//        }
//    }
    public synchronized void increase() {
        i ++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j ++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
