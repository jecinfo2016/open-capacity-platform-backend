package com.open.device.model;

public class InficomboDevtype {

    private Integer id;
    private String devtype;
    private String devtypedesc;
    private String vendor;
    private String appclass;
    private Integer devtypeflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevtype() {
        return devtype;
    }

    public void setDevtype(String devtype) {
        this.devtype = devtype;
    }

    public String getDevtypedesc() {
        return devtypedesc;
    }

    public void setDevtypedesc(String devtypedesc) {
        this.devtypedesc = devtypedesc;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAppclass() {
        return appclass;
    }

    public void setAppclass(String appclass) {
        this.appclass = appclass;
    }

    public Integer getDevtypeflag() {
        return devtypeflag;
    }

    public void setDevtypeflag(Integer devtypeflag) {
        this.devtypeflag = devtypeflag;
    }

    public InficomboDevtype() {
    }

    public InficomboDevtype(String devtype, String devtypedesc, String vendor, String appclass, Integer devtypeflag) {
        this.devtype = devtype;
        this.devtypedesc = devtypedesc;
        this.vendor = vendor;
        this.appclass = appclass;
        this.devtypeflag = devtypeflag;
    }
}
