package com.chenqinhao.cat.common.util;

import java.util.Random;

/**
 * Created by chenqinhao on 2017/7/15.
 */
public class SimpleUUIDUtils {

    public static final String allCharStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";
    public static final String specialChar = "!@#$%^&*()_+";


    public static String generateInterger(int length) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            buffer.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return buffer.toString();
    }

    public static String generateString(int length) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            buffer.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return random.toString();
    }

    public static String generateAllString(int length) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int  i = 0; i < length; i++) {
            buffer.append(allCharStr.charAt(random.nextInt(allCharStr.length())));
        }
        return buffer.toString();
    }

    public static String generateMixString(int length) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length;  i++) {
            buffer.append(allChar.charAt(random.nextInt(letterChar.length())));
        }
        return buffer.toString();
    }

    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    public static String generateZeroString(int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append('0');
        }
        return buffer.toString();
    }

    public static  String toFixdLengthString(long num, int fixdLength) {
        StringBuffer buffer = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdLength - strNum.length() >= 0) {
            buffer.append(generateZeroString(fixdLength - strNum.length()));
        } else {
            throw new RuntimeException(num + " case to fixd length " + fixdLength + " error!");
        }
        buffer.append(strNum);
        return buffer.toString();
    }

    public static String toFixdLengthString(int num, int fixdLength) {
        StringBuffer buffer = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdLength - strNum.length() >= 0) {
            buffer.append(generateZeroString(fixdLength - strNum.length()));
        } else {
            throw new RuntimeException(num + " case to fixd length " + fixdLength + " error!");
        }
        buffer.append(strNum);
        return buffer.toString();
    }

    public static String generatePwdStr() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            buffer.append(allChar.charAt(random.nextInt(letterChar.length())));
            buffer.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        for (int i = 0; i < 2; i++) {
            buffer.append(specialChar.charAt(random.nextInt(specialChar.length())));
        }
        return buffer.toString();
    }
}
