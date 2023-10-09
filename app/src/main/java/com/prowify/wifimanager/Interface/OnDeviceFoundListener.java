package com.prowify.wifimanager.Interface;


import com.prowify.wifimanager.Model.DeviceItem;
import com.prowify.wifimanager.Others.DeviceFinder;

import java.util.List;

public interface OnDeviceFoundListener {
    void onStart(DeviceFinder deviceFinder);
    void onFinished(DeviceFinder deviceFinder, List<DeviceItem> deviceItems);
    void onFailed(DeviceFinder deviceFinder, int errorCode);
}
