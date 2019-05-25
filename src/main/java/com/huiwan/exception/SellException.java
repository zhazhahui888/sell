package com.huiwan.exception;

import com.huiwan.enums.ResultEnum;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 10:31 2019/5/24
 * @Modified By:
 */
public class SellException extends RuntimeException {

    private Integer code;
    private String message;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
