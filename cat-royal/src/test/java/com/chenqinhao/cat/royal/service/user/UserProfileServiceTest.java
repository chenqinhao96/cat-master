package com.chenqinhao.cat.royal.service.user;

import com.chenqinhao.cat.royal.configurer.MybatisConfigurer;
import com.chenqinhao.cat.royal.configurer.MybatisConfigurerTest;
import com.chenqinhao.cat.royal.model.user.UserProfile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by chenqinhao on 2017/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfigurerTest.class)
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @Test
    public void getById() throws Exception {
        UserProfile userProfile = userProfileService.getById("abc");
        Assert.assertNotNull(userProfile);
    }

}