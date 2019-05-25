package com.huiwan.dto;

import lombok.Data;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 10:51 2019/5/24
 * @Modified By:
 */
@Data
public class CartDTO {

    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
