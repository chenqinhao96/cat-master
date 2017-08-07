package other.shiro.chapter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public class LoginLogoutTest {

    @Test
    public void testHelloWorld() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("maoyu", "123");

        try {
            subject.login(token);
        }
        catch (UnknownAccountException e) {
            // 错误的账号
            e.printStackTrace();
        }
        catch (IncorrectCredentialsException e) {
            // 错误的凭证
        }
        catch (ExpiredCredentialsException e) {
            // 过期的凭证
        }
        catch (ExcessiveAttemptsException e) {
            // 失败次数过多
        }
        catch (LockedAccountException e) {
            // 锁定的账号
        }
        catch (DisabledAccountException e) {
            // 禁用的账号
        }
        catch (AuthenticationException e) {
            // 其他
        }

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();
    }

}
