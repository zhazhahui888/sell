package com.huiwan.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2019/3/2.
 */
@Getter
public enum PayStatusEnum {

    WHIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
