package concorrency;

/**
 * Created by caoyx on 2017/4/17 0017.
 */
public class DeamonDemo {
    public static class DeamonT extends Thread {
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DeamonT();
        t.setDaemon(true);//守护线程必须在 start 方法之前设置，否则会报异常
        t.start();
        Thread.sleep(2000);
        //将 t 设置为守护线程，系统中只有 main 是用户线程，当 main 线程结束时，
        // 守护线程也结束
    }
}
