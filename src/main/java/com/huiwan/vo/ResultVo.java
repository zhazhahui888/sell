package com.huiwan.vo;

import lombok.Data;

/**
 * Created by Administrator on 2019/2/28.
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;
}
