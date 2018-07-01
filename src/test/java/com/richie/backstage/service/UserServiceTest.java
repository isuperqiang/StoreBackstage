package java.com.richie.backstage.service;

import com.richie.backstage.domain.User;
import com.richie.backstage.service.UserService;
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
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void login() {
        User user = userService.login("15812344321", "123456");
        assertThat(user, notNullValue());
    }

    @Test
    @Transactional
    public void register() {
        boolean register = userService.register("13543212345", "543123");
        assertThat(register, equalTo(true));
    }

    @Test
    public void isRegistered() {
        boolean registered = userService.isRegistered("15812324356");
        assertThat(registered, equalTo(false));
    }

    @Test
    public void findUserById() {
        User user = userService.findUserById(1);
        assertThat(user, notNullValue());
    }
}