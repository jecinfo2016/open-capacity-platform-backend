package com.open.device.service.impl;

import com.open.device.dao.InficomboDevtypeDao;
import com.open.device.model.InficomboDevtype;
import com.open.device.service.InficomboDevtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InficomboDevtypeServiceImpl implements InficomboDevtypeService {

    @Autowired
    private InficomboDevtypeDao inficomboDevtypeDao;

    @Override
    public int save(InficomboDevtype inficomboDevtype) {
        return inficomboDevtypeDao.save(inficomboDevtype);
    }

    @Override
    public InficomboDevtype getByType(String devtype) {
        return inficomboDevtypeDao.getByType(devtype);
    }
}
