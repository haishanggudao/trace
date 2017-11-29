package cn.rfidcer.util;

import java.util.UUID;

public class UUIDGenerator
{
    public static String generatorUUID()
    {
        String s = UUID.randomUUID().toString();
        //delete "-" 
        return s.replace("-", "");
        
    }

}
