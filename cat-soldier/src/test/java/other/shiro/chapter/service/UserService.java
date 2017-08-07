package other.shiro.chapter.service;

import other.shiro.chapter.entity.User;

import java.util.Set;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public interface UserService {
    User createUser(User user);
    void changePassword(Long userId, String newPassword);
    void correlationRoles(Long userId, Long... roleIds);
    void uncorrelationRoles(Long userId, Long... roleIds);
    User getByUsername(String username);
    Set<String> findRoles(String username);
    Set<String> findPermissions(String username);
}
