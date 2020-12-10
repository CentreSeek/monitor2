package com.yjjk.monitor.entity.viva.sso.request;

import com.yjjk.monitor.utility.PasswordUtils;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-12-09 14:19:13
 **/
@lombok.Data
@Accessors(chain = true)
public class Request {
    private String code;
    private String first_name;
    private String last_name;
    private Integer type;
    private String room_code;

    public Request() {
        this.type = 400;
        this.room_code = "fyhlj";
        this.last_name = "";
    }

    public void setCode(String code) {
        this.code = "vivalnk" + PasswordUtils.generate(code);
    }

}
