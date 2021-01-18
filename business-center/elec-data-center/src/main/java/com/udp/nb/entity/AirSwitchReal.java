package com.udp.nb.entity;

import com.udp.nb.util.JavaBytesUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class AirSwitchReal {
    private String pver; //1
    private String cmd;//1
    private Integer ver;//1
    private Integer ver2;//1
    private Integer len;//2
    private Integer cmdno;//2
    private String mac;//6
    private Integer timezone;//1
    private String hostid;//1
    private long time;
    private List<AirData> dataList;
    @Data
    public class AirData {
        private Integer nno;//0
        private String type;//1
        //电压值 单位伏特 v
        private Integer vol;//2
        //漏电流值 0.1 mA
        private Integer lki;//4
        //功率 w/h
        private Integer pwr;//6
        //温度 0.1 摄氏度
        private Integer tmp;//10
        //电流值 10mA
        private Integer cur;//12
        // N相电流
        private Integer cur2;//16
        // 告警
        private Integer alm;//20
        // 累计电量（无意义）
        private Long ps;//24
    }

    public AirSwitchReal(String body){
        //AC
        byte[] data = JavaBytesUtils.hexToBytes(body);
        int lengh = data.length;
        this.pver = String.valueOf(JavaBytesUtils.byte2int8(data,0));
        this.cmd = String.valueOf(JavaBytesUtils.byte2int8(data,1));
        ver = JavaBytesUtils.byte2int8(data,2);
        this.ver2 = JavaBytesUtils.byte2int8(data,3);
        this.len = JavaBytesUtils.byte2int16(data,4);
        this.cmdno = JavaBytesUtils.byte2int16(data,6);
        this.mac = JavaBytesUtils.byte2String(data,8,6);
        this.timezone = JavaBytesUtils.byte2int8(data,14);
        this.hostid = String.valueOf(JavaBytesUtils.byte2int8(data,15));

        this.time = System.currentTimeMillis()/1000;
        int pos = 20;
        if(ver>=1 && ver<=30){
            //有效数据
            int step = 32;
            dataList = new ArrayList<>();
            while(lengh-pos>=step){
                AirData airData = new AirData();
                System.out.println("index="+pos);
                airData.setNno(JavaBytesUtils.byte2int8(data,pos));
                if(airData.getNno()>=1 && airData.getNno()<=30){ //有效数据
                    airData.setType(JavaBytesUtils.byte2String(data,pos+1,1));
                    airData.setVol(JavaBytesUtils.byte2int16(data,pos+2));
                    airData.setLki(JavaBytesUtils.byte2int16(data,pos+4));
                    airData.setPwr(JavaBytesUtils.byte2int_big(data,pos+6));
                    airData.setTmp(JavaBytesUtils.byte2int16(data,pos+10));
                    airData.setCur(JavaBytesUtils.byte2int_big(data,pos+12));
                    airData.setCur2(JavaBytesUtils.byte2int_big(data,pos+16));
                    airData.setAlm(JavaBytesUtils.byte2int_big(data,pos+20));
                    airData.setPs(JavaBytesUtils.longFrom8Bytes(data,pos+24,false));
                    dataList.add(airData);
                }
                pos+=step;
            }
            System.out.println("left="+JavaBytesUtils.byte2String(data,pos,lengh-pos));
        }
    }
}
