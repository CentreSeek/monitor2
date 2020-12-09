package com.yjjk.monitor.entity.viva.sso.response;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * Auto-generated: 2020-12-09 14:10:50
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
@Data
@Accessors(chain = true)
public class RefreshToken {

    private String user_id;
    private String token;
    private int expiry;
    private int created;

}