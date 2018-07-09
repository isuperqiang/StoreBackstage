package com.richie.backstage.service;

import com.richie.backstage.domain.Store;
import com.richie.backstage.domain.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author richie on 2018.07.01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StoreServiceTest {

    private StoreService storeService;

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @Test
    @Transactional
    public void createStore() {
        Store store = new Store();
        store.setAddress("123");
        store.setAvgPrice("3");
        store.setCategory("电脑");
        User user = new User();
        user.setUserId(1);
        store.setUser(user);
        store.setDescription("ffff");
        store.setSaleFrom("1");
        store.setSaleTo("2");
        store.setPhone("132323");
        store.setName("fff");
        boolean b = storeService.createStore(store);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void updateStore() {
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
        boolean b = storeService.updateStore(store);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void updateStoreLogo() {
        boolean storeLogo = storeService.updateStoreLogo(4, "33");
        assertThat(storeLogo, equalTo(true));
    }

    @Test
    public void queryStore() {
        Store store = storeService.queryStore(1);
        assertThat(store, notNullValue());
    }
}