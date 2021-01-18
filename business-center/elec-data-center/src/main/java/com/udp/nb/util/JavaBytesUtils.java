package com.udp.nb.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

//字符转byte工具集
public class JavaBytesUtils {

    public static byte[] int2byte32(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    public static byte[] int2byte32_big(int res) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (res & 0xff);// 最低位
        targets[2] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[1] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[0] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    public static byte[] int2byte64(long res) {
        byte[] targets = new byte[8];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) ((res >>> 24) & 0xff);// 最高位,无符号右移。
        targets[4] = (byte) ((res >> 32) & 0xff);// 次低位
        targets[5] = (byte) ((res >> 40) & 0xff);// 次低位
        targets[6] = (byte) ((res >> 48) & 0xff);// 次高位
        targets[7] = (byte) (res >>> 56);// 最高位,无符号右移。
        return targets;
    }

    public static byte[] int2byte64_big(long res) {
        byte[] targets = new byte[8];
        targets[7] = (byte) (res & 0xff);// 最低位
        targets[6] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[5] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[4] = (byte) ((res >>> 24) & 0xff);// 最高位,无符号右移。
        targets[3] = (byte) ((res >> 32) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 40) & 0xff);// 次低位
        targets[1] = (byte) ((res >> 48) & 0xff);// 次高位
        targets[0] = (byte) (res >>> 56);// 最高位,无符号右移。
        return targets;
    }

    public static byte[] int2byte8(int res) {
        byte[] targets = new byte[1];
        targets[0] = (byte) (res & 0xff);// 最低位
        return targets;
    }


    public static byte[] int2byte16(int res) {
        byte[] targets = new byte[2];
        targets[0] = (byte) (res & 0xff);
        targets[1] = (byte) ((res >> 8) & 0xff);
        return targets;
    }

    public static byte[] int2byte16_Big(int res) {
        byte[] targets = new byte[2];
        targets[1] = (byte) (res & 0xff);
        targets[0] = (byte) ((res >> 8) & 0xff);
        return targets;
    }

    public static final int byteArrayToInt_Big(byte byt[]) {
        if (byt.length == 1)
            return 0xff & byt[0];
        else if (byt.length == 2)
            return (0xff & byt[0]) << 8 | 0xff & byt[1];
        else if (byt.length >= 4)
            return (0xff & byt[0]) << 24 | (0xff & byt[1]) << 16 | (0xff & byt[2]) << 8 | 0xff & byt[3];
        else
            return 0;
    }

    public static int byte2int(byte[] res) {
        int targets = 0;
        if (res.length >= 4) {
            targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
                    | ((res[2] & 0xff) << 16) | ((res[3] & 0xff) << 24);
        } else if (res.length == 2) {
            targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00);
        } else if (res.length == 1) {
            targets = (res[0] & 0xff);
        }
        return targets;
    }

    public static long byte2int(byte[] res, int pos) {
        long targets = 0;
        if (res.length >= 4 + pos) {
            targets = (res[pos] & 0xff) | ((res[pos + 1] << 8) & 0xff00) // | 表示安位或
                    | ((res[pos + 2] & 0xff) << 16) | ((res[pos + 3] & 0xff) << 24);
        } else if (res.length == pos + 2) {
            targets = (res[pos] & 0xff) | ((res[pos + 1] << 8) & 0xff00);
        } else if (res.length == pos + 1) {
            targets = (res[pos] & 0xff);
        }
        return targets;
    }

    public static int byte2int_small(byte[] res, int pos) {
        int targets = 0;
        targets = (res[pos] & 0xff) | ((res[pos + 1] << 8) & 0xff00) // | 表示安位或
                | ((res[pos + 2] & 0xff) << 16) | ((res[pos + 3] & 0xff) << 24);

        return targets;
    }

    public static int byte2int_big(byte[] res, int pos) {
        return (0xff & res[pos]) << 24 | (0xff & res[pos + 1]) << 16 | (0xff & res[pos + 2]) << 8 | 0xff & res[pos + 3];
    }

    public static long bytesToLong(byte[] input, int offset, boolean littleEndian) {
        // 将byte[] 封装为 ByteBuffer
        ByteBuffer buffer = ByteBuffer.wrap(input, offset, 8);
        if (littleEndian) {
            // ByteBuffer.order(ByteOrder) 方法指定字节序,即大小端模式(BIG_ENDIAN/LITTLE_ENDIAN)
            // ByteBuffer 默认为大端(BIG_ENDIAN)模式
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getLong();
    }

    public static long longFrom8Bytes(byte[] input, int offset, boolean littleEndian) {
        if (offset < 0 || offset + 8 > input.length)
            throw new IllegalArgumentException(String.format("less than 8 bytes from index %d  is insufficient for long", offset));
        long value = 0;
        for (int count = 0; count < 8; ++count) {
            int shift = (littleEndian ? count : (7 - count)) << 3;
            value |= ((long) 0xff << shift) & ((long) input[offset + count] << shift);
        }
        return value;
    }

    public static int byte2int16(byte[] res, int pos) {
        int targets =0 ;
         targets = (res[pos+1] & 0xff) | ((res[pos ] << 8) & 0xff00);
        return targets;
    }

    public static int byte2int8(byte[] res, int pos) {
        int targets = (res[pos] & 0xff);
        return targets;
    }


    /**
     * 将16位的short转换成byte数组
     *
     * @param s short
     * @return byte[] 长度为2
     */
    public static byte[] shortToByteArray(short s) {
        byte[] targets = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((s >>> offset) & 0xff);
        }
        return targets;
    }

    /**
     * 注释：字节数组到short的转换！
     *
     * @param b
     * @return
     */
    public static short byteToShort(byte[] b) {
        short s = 0;
        short s0 = (short) (b[0] & 0xff);// 最低位
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * 把byte[]转换成16进制进制字符串
     *
     * @param b
     * @return
     */
    public static String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    /**
     * byte[]转换成bit
     *
     * @return
     */
    public static String bytesToBits(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            sb.append(byteToBits(b));
        }
        return sb.toString();
    }

    /**
     * byte转换成8位bit
     *
     * @param b
     * @return
     */
    public static String byteToBits(byte b) {
        int z = b;
        z |= 256;
        String str = Integer.toBinaryString(z);
        int len = str.length();
        return str.substring(len - 8, len);
    }

    /**
     * 计算校验和
     *
     * @param bytes
     * @return
     */
    public static final int calculateCheckSum(byte[] bytes) {
        int sum = 0;
        for (byte b : bytes) {
            sum += (short) b;
        }
        return sum > 65535 ? (sum - 65535) : sum;
    }


    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;

    }

    /**
     * 字节转换为浮点
     *
     * @param b     字节（至少4个字节）
     * @param index 开始位置
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }


    public static byte[] double2Bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    public static double bytes2Double(byte[] arr, Integer pos) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i + pos] & 0xff)) << (8 * (i));
        }
        return Double.longBitsToDouble(value);
    }


    public static byte[] hexToBytes(String hex) {
        hex = hex.length() % 2 != 0 ? "0" + hex : hex;

        byte[] b = new byte[hex.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(hex.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static String byte2String(byte[] res, int pos, int length) {
        byte[] bytes = new byte[length];
        System.arraycopy(res, pos, bytes, 0, length);
        return bytesToHex(bytes);
    }


    public static String byteToString(byte[] bytes) {
        String ret = "";
        try {
            ret = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
