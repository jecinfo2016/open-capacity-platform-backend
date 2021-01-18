package com.jecinfo.spims.accept;



import java.util.Queue;
import java.util.concurrent.*;

public class MsgQueue{

    //保存消息的数据容器,队列储存消息的最大容量999
    private static Queue<String> messageQueue = new ArrayBlockingQueue<>(999);

    private static Executor EXECUTOR = Executors.newSingleThreadExecutor(r -> new Thread(r, "thread-" + r.hashCode()));

    //生产消息
    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            // 创建线程池处理队列消息
            EXECUTOR.execute(() -> {
                try {
                    String msg1 = messageQueue.poll();
                    System.out.println("取出了队列msg1的消息:" + msg1 + ";队列剩余消息数:" + messageQueue.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("队列已满");
        }

    }

    //消费消息
    public static String consume() {
        String msg = messageQueue.poll();

        if (msg != null) {
            System.out.println("取出了队列msg的消息:" + msg + ";队列剩余消息数:" + messageQueue.size());
        } else {
            System.out.println("队列是空的");
        }
        return msg;
    }
}
