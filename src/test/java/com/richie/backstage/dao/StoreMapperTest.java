package com.richie.backstage.dao;

import com.richie.backstage.domain.Store;
import com.richie.backstage.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * @author richie on 2018.07.01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StoreMapperTest {

    private StoreMapper storeMapper;

    @Autowired
    public void setStoreMapper(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Test
    @Transactional
    public void createStore() throws SQLException {
        Store store = new Store();
        store.setAddress("123");
        store.setAvgPrice("3");
        store.setCategory("电脑");
        store.setUser(new User(1));
        store.setDescription("ffff");
        store.setSaleFrom("1");
        store.setSaleTo("2");
        store.setPhone("132323");
        store.setName("fff");
        int i = storeMapper.createStore(store);
        assertThat(i, greaterThan(1));
    }

    @Test
    @Transactional
    public void updateStore() throws SQLException {
        Store store = new Store();
        store.setAddress("123");
        store.setAvgPrice("3");
        store.setCategory("电脑");
        store.setStoreId(4);
        store.setDescription("ffff");
        store.setSaleFrom("1");
        store.setSaleTo("2");
        store.setPhone("132323");
        store.setName("fff");
        int i = storeMapper.updateStore(store);
        assertThat(i, greaterThan(0));
    }

    @Test
    public void queryStore() {
        Store store = storeMapper.queryStore(1);
        assertThat(store, notNullValue());
    }

    @Test
    @Transactional
    public void updateStoreLogo() throws SQLException {
        int i = storeMapper.updateStoreLogo(4, "123");
        assertThat(i, greaterThan(0));
    }
}