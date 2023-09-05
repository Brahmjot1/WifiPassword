package com.team.wifimanager.Interface;


import com.team.wifimanager.Model.DeviceItem;
import com.team.wifimanager.Others.DeviceFinder;

import java.util.List;

public interface OnDeviceFoundListener {
    void onStart(DeviceFinder deviceFinder);
    void onFinished(DeviceFinder deviceFinder, List<DeviceItem> deviceItems);
    void onFailed(DeviceFinder deviceFinder, int errorCode);
}
