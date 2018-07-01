package java.com.richie.backstage.service;

import com.richie.backstage.domain.Store;
import com.richie.backstage.service.StoreService;
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
    public void createStore() {
    }

    @Test
    public void updateStore() {
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