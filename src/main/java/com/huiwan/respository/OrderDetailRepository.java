package com.huiwan.respository;

import com.huiwan.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019/3/2.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail , String> {
    /**
     * 根据订单ID查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
