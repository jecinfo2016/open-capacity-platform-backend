package com.udp.nb.entity;

import com.udp.nb.util.JavaBytesUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class AirSwitchData {
    private String pver; //1协议的版本号
    private String cmd;//1类型
    private Integer ver;//1
    private Integer ver2;//1
    private Integer len;//2
    private Integer cmdno;//2
    private String mac;//6
    private Integer timezone;//1
    private String hostid;//1电箱号
    private long time;
    private List<AirData> dataList;
    @Data
    public class AirData {
        private Integer nno;//0tags
        private String type;//1tags
        //电压值 单位伏特 v
        private Integer vol;//2key——value
        //漏电流值 0.1 mA
        private Integer lki;//4不存
        //功率 w/h
        private Integer pwr;//6
        //温度 0.1 摄氏度
        private Integer tmp;//8
        //电流值 10mA
        private Integer cur;//10
        // N相电流
        private Integer cur2;//12
        // 告警
        private Integer alm;//14
        // 累计电量（无意义）
        private long ps;//18不存
    }

    public AirSwitchData(String body){
        //A0
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
            //22
            int step = 22;
            dataList = new ArrayList<>();
            while(lengh-pos>=step){
                AirData airData = new AirData();
                airData.setNno(JavaBytesUtils.byte2int8(data,pos));
                if(airData.getNno()>=1 && airData.getNno()<=30){ //有效数据
                    airData.setType(JavaBytesUtils.byte2String(data,pos+1,1));
                    airData.setVol(JavaBytesUtils.byte2int16(data,pos+2));
                    airData.setLki(JavaBytesUtils.byte2int16(data,pos+4));
                    airData.setPwr(JavaBytesUtils.byte2int16(data,pos+6));
                    airData.setTmp(JavaBytesUtils.byte2int16(data,pos+8));
                    airData.setCur(JavaBytesUtils.byte2int16(data,pos+10));
                    airData.setCur2(JavaBytesUtils.byte2int16(data,pos+12));
                    airData.setAlm(JavaBytesUtils.byte2int_big(data,pos+14));
                    airData.setPs(JavaBytesUtils.byte2int(data,pos+18));
                    dataList.add(airData);
                }
                pos+=step;
            }
        }
    }
}
