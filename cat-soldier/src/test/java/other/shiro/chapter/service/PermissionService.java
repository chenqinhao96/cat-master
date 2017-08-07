package other.shiro.chapter.service;

import other.shiro.chapter.entity.Permission;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public interface PermissionService {
    Permission createPermission(Permission permission);
    void deletePermission(Long permissionId);
}
