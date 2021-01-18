package com.udp.nb.util;

import com.udp.nb.service.NettyService;
import com.udp.nb.entity.NbClientVo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

/**
 * @version 1.0
 * @date 18/3/14 下午9:19
 */
public class NBUtil {

    /**
     * 发送信息
     * @param msg
     * @param ctx
     * @param packet
     */
    public static void doSendMessageByKey(String key,String msg, ChannelHandlerContext ctx, DatagramPacket packet,
                                     boolean isResend, String ip, String port) throws InterruptedException{
       // String key = ip+":"+port;
        NettyService.userSocketMap.remove(key);
        System.out.println("doSendMessageByKey 发送之前清空已处理成功的key:"+key);
        writeMsg(msg, ctx, packet);
        long t = System.currentTimeMillis()/1000;
        if(isResend == true) {
            if(isGetMessage(t, key)) {
                return;
            } else {
                resendMessage(ctx, packet, msg);
            }
        }
    }


    /**
     * 将16进制的字符串转换成byte数组
     * @param hexString
     * @return
     */
    public static byte[] toByteArray(String hexString) {
        if (hexString != null &&  !hexString.equals("") ) {
            hexString = hexString.toLowerCase();
            final byte[] byteArray = new byte[hexString.length() / 2];
            int k = 0;
            for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
                byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
                byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
                byteArray[i] = (byte) (high << 4 | low);
                k += 2;
            }
            return byteArray;
        }
        return null;
    }

    /**
     * 判断客户端3秒内是否有向云端发送信息
     * @param t
     * @param key
     * @return
     */
    public static boolean isGetMessage(long t, String key) {
        while (System.currentTimeMillis() / 1000 < t + 3) {
            if(NettyService.userSocketMap.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 向客户端每3秒钟重发一次消息
     * @param ctx
     * @param packet
     * @param msg
     * @throws InterruptedException
     */
    public static void resendMessage(ChannelHandlerContext ctx, DatagramPacket packet, String msg) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread.sleep(3000);
            System.out.println("报文重发："+i);
            writeMsg(msg, ctx, packet);
        }
    }

    /**
     * 向客户端写信息
     * @param msg
     * @param ctx
     * @param packet
     */
    public static void writeMsg(String msg, ChannelHandlerContext ctx, DatagramPacket packet) throws InterruptedException {
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(NBUtil.toByteArray(msg)), packet.sender())).sync();
    }


    /**
     * 设置nb客户端保存的实体类
     * @param ctx
     * @param packet
     */
    public static NbClientVo putNbClient(ChannelHandlerContext ctx, DatagramPacket packet) {
        NbClientVo nbClient = new NbClientVo();
        nbClient.setCtx(ctx);
        nbClient.setPacket(packet);
        return nbClient;
    }

    /**
     * 判断消息是否是重复上传,15秒内则认为重复
     * @param body
     * @return
     */
    public static boolean isRepeat(String body) {
           String crc = body.substring(body.length() - 8, body.length());
            Long current= System.currentTimeMillis() / 1000;
            Long key= NettyService.nbClientCRCMap.get(crc);
            if(key!=null) {
                if ((current - key) < 15) {
                    return true;
                }else{
                    NettyService.nbClientCRCMap.put(crc,current);
                    return false;
                }
            }else{
                NettyService.nbClientCRCMap.put(crc,current);
                return false;
            }
    }
}
