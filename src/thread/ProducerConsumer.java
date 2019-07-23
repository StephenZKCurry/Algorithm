package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 生产者消费者模式
 * @author: zhukai
 * @date: 2019/7/22 15:23
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        // 产品容器
        ProductContainer1 container1 = new ProductContainer1(20);
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        ProductContainer2 container2 = new ProductContainer2(20, lock, producerCondition, consumerCondition);
        ProductContainer3 container3 = new ProductContainer3(20);
        // 生产者线程
        Producer producer1 = new Producer(container3);
        Producer producer2 = new Producer(container3);
        Producer producer3 = new Producer(container3);
        // 消费者线程
        Consumer consumer1 = new Consumer(container3);
        Consumer consumer2 = new Consumer(container3);
        Consumer consumer3 = new Consumer(container3);

        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
//        consumer2.start();
//        consumer3.start();
    }

    /**
     * 生产者线程
     */
    static class Producer extends Thread {

        private ProductContainer3 container;

        public Producer(ProductContainer3 container) {
            this.container = container;
        }

        @Override
        public void run() {
            // 不断生产
            for (; ; ) {
                container.add();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者线程
     */
    static class Consumer extends Thread {

        private ProductContainer3 container;

        public Consumer(ProductContainer3 container) {
            this.container = container;
        }

        @Override
        public void run() {
            // 不断消费
            for (; ; ) {
                container.remove();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 产品容器类，使用synchronized、wait和notify
     */
    static class ProductContainer1 {
        // 当前数量
        private int num = 0;
        // 允许存放的最大数量
        private int size;

        public ProductContainer1(int size) {
            this.size = size;
        }

        /**
         * 生产者生产产品
         */
        public synchronized void add() {
            if (num < size) {
                // 数量未达到上限
                num++;
                System.out.println("生产了一件产品，当前产品数量为" + num);
                // 通知等待的消费者
                notifyAll();
            } else {
                // 数量已达到上限
                try {
                    // 生产者进入等待状态，并释放锁
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 消费者消费产品
         */
        public synchronized void remove() {
            if (num > 0) {
                // 容器中还有产品
                num--;
                System.out.println("消费了一件产品，当前产品数量为" + num);
                // 通知等待的生产者
                notifyAll();
            } else {
                // 容器中已没有产品
                try {
                    // 消费者进入等待状态，并释放锁
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 产品容器类，使用Lock、await和signalAll
     */
    static class ProductContainer2 {
        // 当前数量
        private int num = 0;
        // 允许存放的最大数量
        private int size;
        // 重入锁对象
        private Lock lock;
        // 生产者条件对象
        private Condition producerCondition;
        // 消费者条件对象
        private Condition consumerCondition;

        public ProductContainer2(int size, Lock lock, Condition producerCondition, Condition consumerCondition) {
            this.size = size;
            this.lock = lock;
            this.producerCondition = producerCondition;
            this.consumerCondition = consumerCondition;
        }

        /**
         * 生产者生产产品
         */
        public void add() {
            lock.lock();
            try {
                if (num < size) {
                    // 数量未达到上限
                    num++;
                    System.out.println("生产了一件产品，当前产品数量为" + num);
                    // 通知等待的消费者
                    consumerCondition.signalAll();
                } else {
                    // 数量已达到上限
                    try {
                        // 生产者进入等待状态，并释放锁
                        producerCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        /**
         * 消费者消费产品
         */
        public void remove() {
            lock.lock();
            try {
                if (num > 0) {
                    // 容器中还有产品
                    num--;
                    System.out.println("消费了一件产品，当前产品数量为" + num);
                    // 通知等待的生产者
                    producerCondition.signalAll();
                } else {
                    // 容器中已没有产品
                    try {
                        // 消费者进入等待状态，并释放锁
                        consumerCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 产品容器类，使用阻塞队列
     */
    static class ProductContainer3 {

        // 当前数量
        private int num = 0;
        private BlockingQueue<Integer> mQueue;

        public ProductContainer3(int size) {
            mQueue = new LinkedBlockingQueue<>(size);
        }

        /**
         * 生产者生产产品
         */
        public void add() {
            if (mQueue.offer(1)) {
                num++;
                System.out.println("生产了一件产品，当前产品数量为" + num);
            }
        }

        /**
         * 消费者消费产品
         */
        public void remove() {
            if (mQueue.poll() != null) {
                num--;
                System.out.println("消费了一件产品，当前产品数量为" + num);
            }
        }
    }
}