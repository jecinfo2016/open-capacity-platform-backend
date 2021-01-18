package com.open.device.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Getter;

/**
 * 中兴克拉-设备控制
 */
@Data
public class Devcontrol {

    private String token;

    private String deveui;

    private String appeui;//broadcastcmd

    private String devtype;

    private boolean confirm;

    private Integer devport;

    private Integer bctimes;//broadcastcmd

    private JSONObject command;

    private String desturl;

    public boolean getConfirm() {
        return confirm;
    }
}
