package other.cipher;

import org.apache.shiro.codec.Base64;
import org.junit.Test;

/**
 * Created by chenqinhao on 2017/7/17.
 */
public class CipherTest {

    @Test
    public void base64() {
        String key = Base64.encodeToString("maoyu-chan".getBytes());
        System.out.println(key);
    }

}
