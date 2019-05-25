package com.huiwan.dto;

import com.huiwan.bean.OrderDetail;
import com.huiwan.enums.OrderStatusEnum;
import com.huiwan.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 9:47 2019/5/24
 * @Modified By:
 */
@Data
public class OrderDTO {

    private String orderId;//订单编号

    private String buyerName;//买家姓名

    private String buyerPhone;//买家电话

    private String buyerAddress;//买家地址

    private String buyerOpenId;//买家微信openId

    private BigDecimal orderAmount;//订单金额

    private Integer orderStatus;//订单状态

    private Integer payStatus;//支付状态

    private List<OrderDetail> orderDetails;
}
