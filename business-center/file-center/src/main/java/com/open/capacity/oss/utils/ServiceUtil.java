package com.open.capacity.oss.utils;

import com.open.capacity.oss.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceUtil {

    @Autowired
    private CatalogService catalogService;

    private static ServiceUtil serviceUtil;

    public void setUserInfo(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @PostConstruct
    public void init(){
    serviceUtil= this;
    serviceUtil.catalogService = this.catalogService;
    }
}
