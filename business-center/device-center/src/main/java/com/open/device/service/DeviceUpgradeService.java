package com.open.device.service;

import com.open.device.model.DeviceUpgrade;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeviceUpgradeService {

    int save(DeviceUpgrade deviceUpgrade);

    List<DeviceUpgrade> findAll();
}
