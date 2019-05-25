package com.huiwan.respository;

import com.huiwan.bean.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019/3/2.
 */
public interface OrderRepository extends JpaRepository<Order , String>{
    /**
     * 根据买家openId查询订单,这里要传一个Pageable对象
     * @param openId , pageable
     * @return
     */
    Page<Order> findByBuyerOpenId(String openId , Pageable pageable);


}
