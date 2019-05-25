package com.huiwan.bean;

import com.huiwan.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/2/27.
 */
@Entity
@Table(name = "product_info")
@Data
public class ProductInfo {

    @Id
    private String productId;//产品编号

    private String productName;//产品名称

    private BigDecimal productPrice;//产品单价

    private Integer productStock;//产品库存

    private String productDescription;//产品描述

    private String productIcon;//产品图片

    private Integer productStatus = ProductStatusEnum.UP.getCode();//产品图片

    private Integer categoryType;//类目编号


}
