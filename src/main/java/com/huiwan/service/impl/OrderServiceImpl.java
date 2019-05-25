package com.huiwan.service.impl;

import com.huiwan.bean.Order;
import com.huiwan.bean.OrderDetail;
import com.huiwan.bean.ProductInfo;
import com.huiwan.converter.Order2OrderDTO;
import com.huiwan.dto.CartDTO;
import com.huiwan.dto.OrderDTO;
import com.huiwan.enums.OrderStatusEnum;
import com.huiwan.enums.PayStatusEnum;
import com.huiwan.enums.ResultEnum;
import com.huiwan.exception.SellException;
import com.huiwan.respository.OrderDetailRepository;
import com.huiwan.respository.OrderRepository;
import com.huiwan.service.OrderService;
import com.huiwan.service.ProductService;
import com.huiwan.utils.GenerateIDUtil;
import com.huiwan.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 9:51 2019/5/24
 * @Modified By:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {
        String orderId = GenerateIDUtil.generateID();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //查询商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //计算订单总金额
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(GenerateIDUtil.generateID());
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //订单入库（主订单，订单详情）
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WHIT.getCode());
        order.setOrderAmount(orderAmount);
        order.setOrderId(orderId);
        orderRepository.save(order);
        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetails().stream().map(e ->
            new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.deleteStock(cartDTOList);
     return orderDTO;
    }

    @Override
    public Page<OrderDTO> findByBuyerOpenId(String openId, Pageable pageable) {
        Page<Order> orderPage = orderRepository.findByBuyerOpenId(openId, pageable);
        List<OrderDTO> orderDTOList = Order2OrderDTO.convert(orderPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderPage.getTotalElements());
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = Order2OrderDTO.convert(orderPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if(!OrderStatusEnum.NEW.equals(orderDTO.getOrderStatus())){
            log.error("【取消订单】订单状态不正确， orderId={} , orderStatus={}" ,orderDTO.getOrderId() , orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(order,orderDTO);
        Order orderResult = orderRepository.save(order);
        if(orderResult == null){
            log.error("【取消订单】订单更新失败，order",orderResult);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //加库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetails().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.addStock(cartDTOList);
        //退款
        //TODO 退款逻辑
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!OrderStatusEnum.NEW.equals(orderDTO.getOrderStatus())){
            log.error("【取消订单】订单状态不正确， orderId={} , orderStatus={}" ,orderDTO.getOrderId() , orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(order,orderDTO);
        Order updateResult = orderRepository.save(order);
        if(updateResult == null){
            log.error("【取消订单】订单更新失败，order",updateResult);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    //TODO 支付逻辑
    @Override
    public OrderDTO pay(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Order order = orderRepository.findOne(orderId);
        if(order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(MyUtils.isBlank(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        orderDTO.setOrderDetails(orderDetailList);
        return orderDTO;
    }
}
