package com.open.device.service;

import com.open.device.model.EpnaetPointDevice;

import java.util.List;

public interface EpnaetPointDeviceService {

    int configurationDevice(EpnaetPointDevice epnaetPointDevice);

    List<EpnaetPointDevice> modelDeviceList(Integer modelId);

    int deleteById(Integer id);
}
