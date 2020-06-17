package com.xsn.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RouteDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
