package java.com.richie.backstage.dao;

import com.richie.backstage.dao.StoreMapper;
import com.richie.backstage.domain.Store;
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
    public void createStore() {
    }

    @Test
    public void updateStore() {
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