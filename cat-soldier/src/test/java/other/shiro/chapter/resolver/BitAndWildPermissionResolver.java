package other.shiro.chapter.resolver;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import other.shiro.chapter.permission.BitPermission;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public class BitAndWildPermissionResolver implements PermissionResolver{
    @Override
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("+")) {
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
