package com.huiwan.respository;

import com.huiwan.bean.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by Administrator on 2019/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = repository.findByOrderId("66666666");
        Assert.assertNotEquals(null,orderDetailList.size());

    }

    @Test
    public void saveTest(){
        /*OrderDetail orderDetail= new OrderDetail();
        orderDetail.setDetailId("88888888");
        orderDetail.setOrderId("66666666");
        orderDetail.setProductIcon("http://xxx/yazhang.jpg");
        orderDetail.setProductId("123689");
        orderDetail.setProductName("卤鸭掌");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(100);
        OrderDetail orderDetail1 = repository.save(orderDetail);
        Assert.assertNotEquals(null,orderDetail);*/
    }
}