package other.shiro.chapter.cipher;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public class Base64Test {

    @Test
    public void test() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String str2 =  Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str,  str2);
    }

    @Test
    public void testHex() {
        String str = "hello";
        String base64Encoded = Hex.encodeToString(str.getBytes());
        String str2 = new String(Hex.decode(base64Encoded.getBytes()));
        Assert.assertEquals(str, str2);
    }



}
