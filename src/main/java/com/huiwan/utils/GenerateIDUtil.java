package com.huiwan.utils;

import java.util.Random;

/**
 * @Author:mjl
 * @Description:生成主键
 * @Date:Create in 10:40 2019/5/24
 * @Modified By:
 */
public class GenerateIDUtil {

    public static synchronized String generateID(){
        Random random = new Random();
        Integer result = random.nextInt(90000)+100000;
        return System.currentTimeMillis()+String.valueOf(result);
    }
}
