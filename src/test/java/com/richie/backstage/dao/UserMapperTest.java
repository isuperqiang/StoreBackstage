package java.com.richie.backstage.dao;

import com.richie.backstage.dao.UserMapper;
import com.richie.backstage.domain.User;
import com.richie.backstage.util.MD5Utils;
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
public class UserMapperTest {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    public void login() {
        User user = userMapper.login("15812344321", MD5Utils.encode("123456"));
        assertThat(user, notNullValue());
    }

    @Test
    @Transactional
    public void register() throws SQLException {
        int register = userMapper.register("18923124321", MD5Utils.encode("654321"));
        assertThat(register, greaterThan(0));
    }

    @Test
    public void findCountByPhone() {
        int count = userMapper.findCountByPhone("15812344321");
        assertThat(count, greaterThan(0));
    }

    @Test
    public void findUserById() {
        User user = userMapper.findUserById(1);
        assertThat(user, notNullValue());
    }

    @Test
    @Transactional
    public void updateVisitTime() throws SQLException {
        int i = userMapper.updateVisitTime(1);
        assertThat(i, greaterThan(0));
    }

    @Test
    public void findLastUserId() {
        int userId = userMapper.findLastUserId();
        assertThat(userId, notNullValue());
    }
}