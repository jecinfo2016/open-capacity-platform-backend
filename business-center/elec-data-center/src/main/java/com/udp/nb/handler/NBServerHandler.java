package com.udp.nb.handler;

import com.udp.nb.util.CRC16Util;
import com.udp.nb.util.MessageUtil;
import com.udp.nb.util.NBUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author cloudy
 * @version 1.0
 * UDP接收数据处理类
 * @date 18/1/21 下午7:41
 */
@Component
public class NBServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static Logger logger = LoggerFactory.getLogger(NBServerHandler.class);

    @Autowired
    private AlarmHandler alarmHandler;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        logger.info("日志-------------------");
        // 读取收到的数据
        String ip = packet.sender().getHostString();
        String port = String.valueOf(packet.sender().getPort());
        ByteBuf buf = (ByteBuf) packet.copy().content();
        //读法2 16进制方式读取请求数据
        String body=CRC16Util.hexToString(buf);
        String mac=MessageUtil.getMac(body);
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>缓存通讯模块地址信息，用于控制下发");
        /**
         * 开启异步处理数据
         */
        alarmHandler.handlerNBClientMapUdp(body, ctx, packet, ip, port);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
