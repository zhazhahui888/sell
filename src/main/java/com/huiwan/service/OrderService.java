package com.huiwan.service;

import com.huiwan.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 9:35 2019/5/24
 * @Modified By:
 */

public interface OrderService {
    //创建订单
    OrderDTO save(OrderDTO orderDTO);

    //根据用户openId查询订单列表
    Page<OrderDTO> findByBuyerOpenId(String openId , Pageable pageable);

    //查询所有订单
    Page<OrderDTO> findAll(Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //接单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO pay(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);
}
