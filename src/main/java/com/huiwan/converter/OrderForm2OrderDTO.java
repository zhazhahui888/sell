package com.huiwan.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huiwan.bean.OrderDetail;
import com.huiwan.dto.OrderDTO;
import com.huiwan.enums.ResultEnum;
import com.huiwan.exception.SellException;
import com.huiwan.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 9:26 2019/5/25
 * @Modified By:
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenId(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误 ， string = {}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(orderDetailList);
        return orderDTO;
    }
}
