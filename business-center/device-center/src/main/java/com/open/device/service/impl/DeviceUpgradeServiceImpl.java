package com.open.device.service.impl;

import com.open.device.dao.DeviceUpgradeDao;
import com.open.device.model.DeviceUpgrade;
import com.open.device.service.DeviceUpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceUpgradeServiceImpl implements DeviceUpgradeService {


    @Autowired
    private DeviceUpgradeDao deviceUpgradeDao;

    @Override
    public int save(DeviceUpgrade deviceUpgrade) {
        return deviceUpgradeDao.save(deviceUpgrade);
    }

    @Override
    public List<DeviceUpgrade> findAll() {
        return deviceUpgradeDao.findAll();
    }
}
