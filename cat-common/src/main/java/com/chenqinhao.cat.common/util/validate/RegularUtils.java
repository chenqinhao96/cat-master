package com.chenqinhao.cat.common.util.validate;

import java.util.regex.Pattern;

/**
 * Created by chenqinhao on 2017/7/15.
 */
public class RegularUtils {

    public static Boolean validatePattern(String regular, String input) {
        Pattern p = Pattern.compile(regular);
        return p.matcher(input).matches();
    }

}
