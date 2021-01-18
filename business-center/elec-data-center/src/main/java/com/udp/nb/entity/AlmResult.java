package com.udp.nb.entity;


import com.udp.nb.util.AlmTypeEnum;

/**
 * @author paul
 * @description
 * @date 2019/6/25
 */
public class AlmResult{
    public int type;
    public String desc;
    public Boolean status;
    public AlmTypeEnum almTypeEnum;
    public int platformType;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AlmTypeEnum getAlmTypeEnum() {
        return almTypeEnum;
    }

    public void setAlmTypeEnum(AlmTypeEnum almTypeEnum) {
        this.almTypeEnum = almTypeEnum;
    }

    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }
}
