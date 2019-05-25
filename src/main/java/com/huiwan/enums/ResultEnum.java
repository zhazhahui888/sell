package com.huiwan.enums;

import lombok.Data;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 10:32 2019/5/24
 * @Modified By:
 */

public enum  ResultEnum {

    PRODUCT_NOT_EXIT(1,"商品不存在"),
    PRODUCT_STOCK_ENOUGH(2,"库存不足"),
    ORDER_NOT_EXIT(3,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(4,"订单不存在"),
    ORDER_STATUS_ERROR(5,"订单状态错误"),
    ORDER_UPDATE_FAIL(6,"订单更新失败"),
    PARAM_ERROR(7,"参数错误"),
    CART_EMPTY(8,"购物车为空")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
