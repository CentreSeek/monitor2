package com.yjjk.monitor.entity.viva.sso.response;
import java.util.List;

import lombok.experimental.Accessors;
/**
 * Auto-generated: 2020-12-09 14:10:50
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
@lombok.Data
@Accessors(chain = true)
public class Data {

    private String id;
    private String code;
    private String first_name;
    private String last_name;
    private String full_name;
    private String username;
    private String vseeid;
    private String email;
    private boolean active;
    private boolean tos;
    private int status;
    private String subtype;
    private String timezone;
    private boolean email_verified;
    private String account_code;
    private List<String> clinics;
    private List<Rooms> rooms;
    private Token token;
}