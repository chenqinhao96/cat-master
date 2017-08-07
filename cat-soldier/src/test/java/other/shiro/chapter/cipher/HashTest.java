package other.shiro.chapter.cipher;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

/**
 * Created by chenqinhao on 2017/7/19.
 */
public class HashTest {

    @Test
    public void md5Hash() {
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str, salt).toString();
        String md5Hex = new Md5Hash(str, salt).toHex();
        String md5Base = new Md5Hash(str, salt).toBase64();
        System.out.println(md5);
        System.out.println(md5Hex);
        System.out.println(md5Base);
    }

    @Test
    public void sha256Test() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();
    }

    @Test
    public void digest() {
        String str = "hello";
        String salt = "123";
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
    }

    @Test
    public void hashServiceTest() {
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(1);
        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }

    @Test
    public void testCode() {
        String keu = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println(keu);
    }
}
