package other.shiro.chapter.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.doCredentialsMatch(token, info);
    }
}
