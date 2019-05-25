package com.huiwan.bean;

import com.huiwan.enums.OrderStatusEnum;
import com.huiwan.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/3/2.
 */
@Entity
@Table(name = "order_master")
@Data
public class Order {

    @Id
    private String orderId;//订单编号

    private String buyerName;//买家姓名

    private String buyerPhone;//买家电话

    private String buyerAddress;//买家地址
    @Column(name = "buyer_openid")
    private String buyerOpenId;//买家微信openId

    private BigDecimal orderAmount;//订单金额

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();//订单状态

    private Integer payStatus = PayStatusEnum.WHIT.getCode();//支付状态
}
