package com.open.device.service;

import com.open.device.model.InficomboDevtype;

public interface InficomboDevtypeService {

    int save(InficomboDevtype inficomboDevtype);

    InficomboDevtype getByType(String devtype);

}
