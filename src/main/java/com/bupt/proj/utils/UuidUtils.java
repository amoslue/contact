package com.bupt.proj.utils;

import java.util.UUID;

public class UuidUtils {
    public static String next(){
        return UUID.randomUUID().toString().replace("-","a");
    }

}