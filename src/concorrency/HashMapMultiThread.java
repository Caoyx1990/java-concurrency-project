package concorrency;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoyx on 2017/4/21 0021.
 */
public class HashMapMultiThread {
    static Map<String, String> map = new HashMap<String, String>();

    public static class AddThread implements Runnable {
        int start = 0;
        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 100000; i += 2) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread d1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread d2 = new Thread(new HashMapMultiThread.AddThread(1));
        d1.start();
        d2.start();
        d1.join();
        d2.join();
        System.out.println(map.size());
    }
}
