package com.chenqinhao.cat.common.util;

import org.jboss.netty.handler.codec.base64.Base64Encoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES加解密工具类
 * Created by chenqinhao on 2017/9/3.
 */
public class AESUtils {

    private static final String encodeRules = "maoyu";

    /**
     * 加密
     * @param content
     * @return
     */
    public static String AESEncode(String content) {

        try {
            // 构建秘钥生成器, 指定为AES算法
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 根据encodeRules规则初始化秘钥生成器
            // 生成一个128位的随机源, 根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            // 产生原始对称密钥
            SecretKey orginalKey = keygen.generateKey();
            // 获得原始对称密匙的字节数组
            byte[] raw = orginalKey.getEncoded();
            // 根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            // 根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化密码器, 第一个参数为加密(Encrypt mode)或者解密(Decrypt mode)操作, 第二参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes("utf-8");
            // 根据密码器的初始化方式--加密: 将数据加密
            byte[] byteAES = cipher.doFinal(byteEncode);
            // 将加密后的数据转换为字符串
            String aesEncode = new String(new BASE64Encoder().encode(byteAES));
            return aesEncode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密
     * @param content
     * @return
     */
    public static String AESDecode(String content) {
        try {
            // 构造密钥生成器, 指定为AES算法, 不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 根据encodeRules规则初始化密钥生成器
            // 生成一个128的随机源, 根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            SecretKey originalKey = keygen.generateKey();
            byte[] row = originalKey.getEncoded();
            SecretKey key = new SecretKeySpec(row, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
            byte[] byteDecode = cipher.doFinal(byteContent);
            String aesDecode = new String(byteDecode, "utf-8");
            return aesDecode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
