package com.chenqinhao.cat.royal.dao.user;

import com.chenqinhao.cat.royal.model.user.UserProfile;

/**
 * Created by chenqinhao on 2017/7/4.
 */
public interface UserProfileDao {
    UserProfile getById(String id);
}
