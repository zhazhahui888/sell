package com.huiwan.service.impl;

import com.huiwan.bean.OrderDetail;
import com.huiwan.dto.OrderDTO;
import com.huiwan.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 14:05 2019/5/24
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Test
    public void save() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("毛建林");
        orderDTO.setBuyerPhone("15270261905");
        orderDTO.setBuyerAddress("西河一区");
        orderDTO.setBuyerOpenId("5201314789");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail("123456",2);
        OrderDetail orderDetail1 = new OrderDetail("123466",2);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);
        orderDTO.setOrderDetails(orderDetailList);
        OrderDTO order = orderService.save(orderDTO);
        System.out.println(order);
    }

    @Test
    public void findByBuyerOpenId() throws Exception {
        PageRequest pageRequest = new PageRequest(0,10);

    }

    @Test
    public void findOne() throws Exception {
        OrderDTO one = orderService.findOne("1558680003405102021");
        System.out.println(one);
    }

}