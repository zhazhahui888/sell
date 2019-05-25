package com.huiwan.respository;

import com.huiwan.bean.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;
    @Test
    public void findByBuyerOrderId() throws Exception {
    }

    @Test
    public void saveTest(){
        Order order = new Order();
        order.setOrderId("66666666");
        order.setBuyerName("毛哥");
        order.setBuyerPhone("15270261905");
        order.setBuyerAddress("大塘下三区");
        order.setOrderId("mjl666");
        order.setOrderAmount(new BigDecimal(26.66));
        Order order1 = repository.save(order);
        Assert.assertNotEquals(null,order1);

    }
}