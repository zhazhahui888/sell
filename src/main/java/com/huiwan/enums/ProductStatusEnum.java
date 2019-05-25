package com.huiwan.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2019/2/27.
 */
@Getter
public enum  ProductStatusEnum{
    UP(0,"在架"),
    DOWN(1,"下架");

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;
}
