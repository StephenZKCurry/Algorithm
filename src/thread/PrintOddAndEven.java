package thread;

/**
 * @description: 两个线程交替打印奇偶数
 * @author: zhukai
 * @date: 2019/7/22 9:20
 */
public class PrintOddAndEven {

    public static void main(String[] args) {
        Object object = new Object();
        Thread printOddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    for (int i = 1; i <= 10; i += 2) {
                        System.out.println(i);
                        object.notify();
                        try {
                            object.wait();
                            // 防止打印速度过快
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread printEvenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    for (int i = 2; i <= 10; i += 2) {
                        System.out.println(i);
                        object.notify();
                        try {
                            object.wait();
                            // 防止打印速度过快
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        printOddThread.start();
        printEvenThread.start();
    }
}
