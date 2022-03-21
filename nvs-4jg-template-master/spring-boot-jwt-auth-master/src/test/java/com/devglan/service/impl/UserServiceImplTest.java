package com.devglan.service.impl;

import com.devglan.config.WebSecurityConfig;
import com.devglan.model.User;
import com.devglan.model.UserDto;
import com.devglan.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        String username = "user2";
        String firstname = "firstname2";

        UserDto user = new UserDto();
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName("lastname2");
        user.setPassword("bla2");
        userService.save(user);

        Optional<User> foundUser = userService.findAll().stream().filter(u -> u.getUsername().equals(username)).findFirst();

        Assert.assertTrue(foundUser.isPresent());
        Assert.assertEquals(username, foundUser.get().getUsername());
        Assert.assertEquals(firstname, foundUser.get().getFirstName());
    }

}
