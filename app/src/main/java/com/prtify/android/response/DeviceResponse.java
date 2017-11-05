package com.prtify.android.response;

import java.util.List;

/**
 * Created by satya on 11/4/17.
 */

public class DeviceResponse {

    final List<Device> devices;

    public DeviceResponse(List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }
}
