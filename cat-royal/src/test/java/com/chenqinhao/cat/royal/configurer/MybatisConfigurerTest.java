package com.chenqinhao.cat.royal.configurer;

import com.chenqinhao.cat.royal.model.user.UserProfile;
import com.chenqinhao.cat.royal.service.user.UserProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.sql.SQLException;

/**
 * Created by chenqinhao on 2017/7/3.
 */
@Configuration
@ComponentScan(basePackages = "com.chenqinhao.cat.royal")
public class MybatisConfigurerTest {
}