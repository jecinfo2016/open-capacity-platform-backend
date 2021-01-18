package com.udp.nb.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @date 18/3/14 下午5:28
 */
@Component
public class NBClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private Logger logger = LoggerFactory.getLogger(NBClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        logger.info("日志");
        String response = msg.content().toString(CharsetUtil.UTF_8);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>返回信息：" + response);
        ctx.close();
    }
}
