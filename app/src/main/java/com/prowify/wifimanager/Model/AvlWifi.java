package com.prowify.wifimanager.Model;

public class AvlWifi {

    String wifiName,wifiMac,wifiCapable,wifiLevel,wifiFrn,wifiSigneltype;


    public AvlWifi(String wifiName, String wifiMac, String wifiCapable, String wifiLevel, String wifiFrn, String wifiSigneltype) {
        this.wifiName = wifiName;
        this.wifiMac = wifiMac;
        this.wifiCapable = wifiCapable;
        this.wifiLevel = wifiLevel;
        this.wifiFrn = wifiFrn;
        this.wifiSigneltype = wifiSigneltype;
    }

    public String getWifiSigneltype() {
        return wifiSigneltype;
    }

    public void setWifiSigneltype(String wifiSigneltype) {
        this.wifiSigneltype = wifiSigneltype;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac;
    }

    public String getWifiCapable() {
        return wifiCapable;
    }

    public void setWifiCapable(String wifiCapable) {
        this.wifiCapable = wifiCapable;
    }

    public String getWifiLevel() {
        return wifiLevel;
    }

    public void setWifiLevel(String wifiLevel) {
        this.wifiLevel = wifiLevel;
    }

    public String getWifiFrn() {
        return wifiFrn;
    }

    public void setWifiFrn(String wifiFrn) {
        this.wifiFrn = wifiFrn;
    }
}
