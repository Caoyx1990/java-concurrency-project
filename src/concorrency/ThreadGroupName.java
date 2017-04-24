package concorrency;

/**
 * Created by caoyx on 2017/4/12 0012.
 */
public class ThreadGroupName implements Runnable {
    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //创建线程组
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        //将t1、t2两个线程加入到线程组中
        Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        //获取活动线程总数，但是只是一个估计值
        System.out.println(tg.activeCount());
        //打印线程组中的所有的线程信息
        tg.list();
    }
}
