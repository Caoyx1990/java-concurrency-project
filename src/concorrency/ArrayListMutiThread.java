package concorrency;

import java.util.ArrayList;

/**
 * Created by caoyx on 2017/4/17 0017.
 */
public class ArrayListMutiThread {

    static ArrayList list = new ArrayList<Integer>(10);
    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
    }
}
