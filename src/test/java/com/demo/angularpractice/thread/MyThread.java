package com.demo.angularpractice.thread;

import org.junit.Test;

public class MyThread {

    private int products = 0;
    private int MAX_PRODUCTS = 100000;
    private int MIN_PRODUCTS = 10;

    synchronized void producer() {
        if (this.products >= MAX_PRODUCTS) {
            try {
                wait();
                System.out.println("生产者停止。已经达到最大值");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.products++;
            System.out.println("生产者生产中。。第" + products + "个");
            notifyAll();
        }

    }

    synchronized void consumer() {
        if (this.products <= MIN_PRODUCTS) {
            try {
                wait();
                System.out.println("消费者停止。总数已达到最低值");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.products--;
            System.out.println("消费者消费中。。第" + this.products + "个");
            notifyAll();
        }
    }

    @Test
    public void testThread() {
//        MyThread myThread = new MyThread();
//        myThread.producer();
//        myThread.consumer();
//        //        new Thread("producer").start();
//        MyThread myThread = new MyThread();
//        Thread thread1 = new Thread(new Thread1(myThread, "producer"));
//        Thread thread2 = new Thread(new Thread2(myThread, "consumer"));
//        while (true) {
//            thread1.start();
//            thread2.start();
//        }
//
//        Runnable thread2 = new Thread2(myThread, "consumer");
//        thread2.run();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        executorService.execute(new Thread1(myThread, "producer"));
//
//        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
//        executorService2.execute(new Thread2(myThread, "consumer"));

        Thread thread1 = new Thread(new ThreadDemo01());
        thread1.setName("thread-1");
        Thread thread2 = new Thread(new ThreadDemo01());
        thread2.setName("thread-2");

        thread1.start();
        thread2.start();

    }
}

class Thread1 implements Runnable {
    MyThread myThread;
    String name;

    Thread1(MyThread myThread, String name) {
        this.myThread = myThread;
        this.name = name;
    }

    @Override
    public void run() {
        MyThread myThread = new MyThread();
        myThread.producer();
    }
}

class Thread2 implements Runnable {
    MyThread myThread;
    String name;

    Thread2(MyThread myThread, String name) {
        this.myThread = myThread;
        this.name = name;
    }

    @Override
    public void run() {
        MyThread myThread = new MyThread();
        myThread.consumer();
    }
}


class ThreadDemo01 implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (i <= 99999) {
            System.out.println(Thread.currentThread().getName() + i++);
        }
    }
}

class ThreadDemo02 implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (i <= 99999) {
            System.out.println(Thread.currentThread().getName() + i++);
        }
    }
}