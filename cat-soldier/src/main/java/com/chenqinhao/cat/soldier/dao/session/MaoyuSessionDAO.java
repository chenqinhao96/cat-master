package com.chenqinhao.cat.soldier.dao.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by chenqinhao on 2017/7/17.
 */
@Service
public class MaoyuSessionDAO extends CachingSessionDAO {


    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {
        return null;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }
}
