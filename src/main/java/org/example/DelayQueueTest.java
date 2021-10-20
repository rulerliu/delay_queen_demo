package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2021-10-20 12:46:00");
        System.out.println(new Date().toLocaleString());
        // 创建延时队列
        DelayQueue<Order> queue = new DelayQueue<Order>();
        // 添加延时消息,m1 延时3s
        Order m1 = new Order(1, "world1", "body1", 3, TimeUnit.SECONDS);
        // 添加延时消息,m2 延时10s
        Order m2 = new Order(2, "hello2", "body2", 10, TimeUnit.SECONDS);
        long duration = date.getTime() - System.currentTimeMillis();
        System.out.println(duration);
        Order m3 = new Order(4, "hello3", "body3", duration, TimeUnit.MILLISECONDS);
        //将延时消息放到延时队列中
        queue.offer(m3);
        queue.offer(m2);
        queue.offer(m1);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new Consumer(queue));
        System.out.println("shutdown 1");
        exec.shutdown();
        System.out.println("shutdown 2");
    }
}  
