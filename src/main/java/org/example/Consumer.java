package org.example;

import java.util.Date;
import java.util.concurrent.DelayQueue;
  
public class Consumer implements Runnable {  
    // 延时队列 ,消费者从其中获取消息进行消费  
    private DelayQueue<Order> queue;
  
    public Consumer(DelayQueue<Order> queue) {
        this.queue = queue;  
    }  
  
    @Override  
    public void run() {  
        while (true) {  
            try {
                Order take = queue.take();
                System.out.println("消费消息name：" + take.getName() + " 消息体：" + take.getTime() + " 时间：" + new Date().toLocaleString());
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
} 