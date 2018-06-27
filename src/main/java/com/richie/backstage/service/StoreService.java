package com.richie.backstage.service;

import com.richie.backstage.dao.StoreMapper;
import com.richie.backstage.domain.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author richie on 2018.06.25
 */
@Service
public class StoreService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private StoreMapper storeMapper;

    @Autowired
    public void setStoreMapper(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public boolean createStore(Store store) {
        try {
            int key = storeMapper.createStore(store.getName(), store.getLogo(), store.getAddress(), store.getCategory(), store.getDescription(),
                    store.getPhone(), store.getAvgPrice(), store.getSaleFrom(), store.getSaleTo(), store.getUser().getUserId());
            return key > 0;
        } catch (SQLException e) {
            logger.error("create store failed", e);
        }
        return false;
    }

    @CacheEvict(value = "updateStore", key = "'storeInfo'")
    public boolean updateStore(Store store) {
        try {
            int affected = storeMapper.updateStore(store.getName(), store.getLogo(), store.getAddress(), store.getCategory(), store.getDescription(),
                    store.getPhone(), store.getAvgPrice(), store.getSaleFrom(), store.getSaleTo(), store.getUser().getUserId());
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update store failed", e);
        }
        return false;
    }

    @Cacheable(value = "queryStore", key = "'storeInfo'")
    public Store queryStore(int userId) {
        return storeMapper.queryStore(userId);
    }
}
