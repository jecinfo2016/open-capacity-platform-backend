package com.open.device.service.impl;

import com.open.device.dao.EpnaetPointDeviceDao;
import com.open.device.model.EpnaetPointDevice;
import com.open.device.service.EpnaetPointDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpnaetPointDeviceServiceImpl implements EpnaetPointDeviceService {

    @Autowired
    private EpnaetPointDeviceDao epnaetPointDeviceDao;
    @Override
    public int configurationDevice(EpnaetPointDevice epnaetPointDevice) {
        return epnaetPointDeviceDao.configurationDevice(epnaetPointDevice);
    }

    @Override
    public List<EpnaetPointDevice> modelDeviceList(Integer modelId) {
        return epnaetPointDeviceDao.modelDeviceList(modelId);
    }

    @Override
    public int deleteById(Integer id) {
        return epnaetPointDeviceDao.deleteById(id);
    }
}
