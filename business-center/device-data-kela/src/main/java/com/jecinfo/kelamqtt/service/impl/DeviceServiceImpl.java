package com.jecinfo.kelamqtt.service.impl;

import com.jecinfo.kelamqtt.dao.DeviceDao;
import com.jecinfo.kelamqtt.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lkq
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public List<String> getByModel() {
        List<String> byModel = deviceDao.findByModel();
        ArrayList<String> strings = new ArrayList<>();
        byModel.forEach(a->{
            a = "jecinfo/"+a;
            strings.add(a);
        });
        return strings;
    }
}
