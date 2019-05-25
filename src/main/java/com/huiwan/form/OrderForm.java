package com.huiwan.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 9:10 2019/5/25
 * @Modified By:
 */
@Data
public class OrderForm {

    /*买家姓名*/
    @NotEmpty(message = "姓名必填")
    private String name;
    /*买家电话*/
    @NotEmpty(message = "电话必填")
    private String phone;
    /*买家地址*/
    @NotEmpty(message = "地址必填")
    private String address;
    /*买家openid*/
    @NotEmpty(message = "openId必填")
    private String openid;
    /*买家购物车*/
    private String items;
}
