package com.jecinfo.spims.accept;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@Component
@PropertySource("classpath:application.yml")
public class DelongServerSocket {
    @Value("${socket.port}")
    private Integer port;
    private boolean started;
    private ServerSocket ss;
    public static ConcurrentHashMap<String, ClientSocket> clientsMap = new ConcurrentHashMap<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private static Logger logger = LoggerFactory.getLogger(DelongServerSocket.class);
    public void start() {
        start(null);
    }

    public void start(Integer port) {
        try {
            ss = new ServerSocket(port == null ? this.port : port);
            started = true;
            System.out.println("端口已开启,占用"+this.port+"端口号....");
        } catch (Exception e) {
            System.out.println("端口使用中....");
            System.out.println("请关掉相关程序并重新运行服务器！");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            Socket socket = ss.accept();
            while (started) {
                socket.setKeepAlive(true);
                ClientSocket register = ClientSocket.register(socket);
                System.out.println("a client connected!");
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                assert register != null;
                String str = register.getMessage();
                if (str == null|| "".equals(str)) {
                    break;
                }
                logger.info("接收到数据"+str);
//                TODO 接受到的消息为 CONSUME的时候为读取，会将消息通过socket发送回去
                if ("CONSUME".equals(str)) {
                    String msg = MsgQueue.consume();
                    out.println(msg);
                    out.flush();
                } else {
                    MsgQueue.produce(str);
                }
                executorService.submit(register);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
