package com.yjjk.monitor.utility;

import java.math.BigDecimal;

/**
 * @program: monitor
 * @description:
 * @author: CentreS
 * @create: 2020-04-08 09:57:21
 **/
public class MathUtils {

    /**
     *
     * <p>华氏度  = 32 + 摄氏度 × 1.8。</p>
     * @param degree 需要转换的温度
     * @param scale 保留的小数位
     * @return
     */
    public static double centigrade2Fahrenheit(double degree,int scale) {
        double d = 32 + degree * 1.8;
        return new BigDecimal(d).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
