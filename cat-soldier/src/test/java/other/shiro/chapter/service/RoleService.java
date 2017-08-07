package other.shiro.chapter.service;

import other.shiro.chapter.entity.Role;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public interface RoleService {
    Role createRole(Role role);
    void deleteRole(Long roleId);
    void correlationPermissions(Long roleId, Long... permissionIds);
    void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
