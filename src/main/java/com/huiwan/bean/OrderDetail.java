package com.huiwan.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/3/2.
 */
@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;//产品名称

    private BigDecimal productPrice;//产品单价
    /** 商品数量*/
    private Integer productQuantity;//产品数量

    private String productIcon;//产品图片

    public OrderDetail(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
