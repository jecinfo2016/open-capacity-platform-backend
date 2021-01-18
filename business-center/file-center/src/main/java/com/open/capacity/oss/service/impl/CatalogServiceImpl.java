package com.open.capacity.oss.service.impl;

import com.open.capacity.oss.dao.CatalogDao;
import com.open.capacity.oss.model.Catalog;
import com.open.capacity.oss.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Override
    public Catalog findCatalog(String id) {
        return catalogDao.findCatalog(id);
    }

    @Override
    public int save(Catalog catalog) {
        return catalogDao.save(catalog);
    }

    @Override
    public int update(Catalog catalog) {
        return catalogDao.update(catalog);
    }

    @Override
    public int delete(String id) {
        return catalogDao.delete(id);
    }
}
