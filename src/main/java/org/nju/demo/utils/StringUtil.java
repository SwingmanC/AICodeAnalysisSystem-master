package org.nju.demo.utils;

import java.util.UUID;

public class StringUtil {
    public static String generateStringId(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }
}
