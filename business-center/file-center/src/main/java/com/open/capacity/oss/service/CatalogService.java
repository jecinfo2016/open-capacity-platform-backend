package com.open.capacity.oss.service;

import com.open.capacity.oss.model.Catalog;

public interface CatalogService {


    Catalog findCatalog(String id);

    int save(Catalog catalog);

    int update(Catalog catalog);

    int delete(String id);
}
