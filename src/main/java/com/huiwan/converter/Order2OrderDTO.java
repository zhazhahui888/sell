package com.huiwan.converter;

import com.huiwan.bean.Order;
import com.huiwan.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 11:33 2019/5/24
 * @Modified By:
 */
public class Order2OrderDTO {

    public static OrderDTO convert(Order order){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<Order> orders){
        List<OrderDTO> orderDTOList = orders.stream().map(e ->
            convert(e)
        ).collect(Collectors.toList());
        return orderDTOList;
    }
}
