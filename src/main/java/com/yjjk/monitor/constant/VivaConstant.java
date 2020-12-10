package com.yjjk.monitor.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-12-09 14:21:29
 **/
public class VivaConstant {
    public static final String ssoRequestUrl = "https://api.vsee.me/api_v3/users/sso.json";
    public static final String vivaRequestUrlFormat = "https://vivalnk.vsee.me/auth?sso_token=%s&next=/providers/patient/%s";
    public static final Map<Integer, String[]> map = new HashMap();

}
