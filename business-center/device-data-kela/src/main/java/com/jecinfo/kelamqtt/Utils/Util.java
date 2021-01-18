package com.jecinfo.kelamqtt.Utils;

public class Util {

    public static byte[] hexStringToString(String hexstring) {

        byte[] destByte = new byte[hexstring.length()/2];
        int j=0;
        for(int i=0;i<destByte.length;i++) {
            byte high = (byte) (Character.digit(hexstring.charAt(j), 16) & 0xff);
            byte low = (byte) (Character.digit(hexstring.charAt(j + 1), 16) & 0xff);
            destByte[i] = (byte) (high << 4 | low);
            j+=2;
        }
        return destByte;
    }
}
