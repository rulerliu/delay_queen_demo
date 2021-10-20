package org.example;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Order implements Delayed {
    private Integer id;
    private String name;
    private String body; // 消息内容
    private long time;

    public Order(Integer id, String name, String body, long time, TimeUnit unit) {
        this.id = id;
        this.name = name;
        this.body = body;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Order order = (Order) o;
        return this.time - order.time > 1 ? 1 : (this.time - order.time < 1 ? -1 : 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", time=" + time +
                '}';
    }
}
