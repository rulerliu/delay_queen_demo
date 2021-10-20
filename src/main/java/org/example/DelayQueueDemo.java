package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Order Order1 = new Order(1, "Order1", "body1", 5, TimeUnit.SECONDS);
        Order Order2 = new Order(2, "Order2", "body2",10, TimeUnit.SECONDS);
        Order Order3 = new Order(3, "Order3", "body3",15, TimeUnit.SECONDS);
        DelayQueue<Order> delayQueue = new DelayQueue<>();
        delayQueue.offer(Order1);
        delayQueue.offer(Order2);
        delayQueue.offer(Order3);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (delayQueue.size() != 0) {
            /**
             * 取队列头部元素是否过期
             */
            System.out.println(">>>阻塞start");
            Order task = delayQueue.take();
            System.out.println(">>>阻塞end");
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }
        System.out.println("end");
    }
}