package com.chenqinhao.cat.royal.service.user.impl;

import com.chenqinhao.cat.royal.dao.user.UserProfileDao;
import com.chenqinhao.cat.royal.model.user.UserProfile;
import com.chenqinhao.cat.royal.service.user.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenqinhao on 2017/7/4.
 */
@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public UserProfile getById(String id) {
        return userProfileDao.getById(id);
    }

}
