package other.shiro.chapter.cipher;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.junit.Test;

import java.security.Key;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public class AesTest {

    @Test
    public void aesCipherTest() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        String encrptTest = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        System.out.println(encrptTest);
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptTest), key.getEncoded()).getBytes());
    }

}
