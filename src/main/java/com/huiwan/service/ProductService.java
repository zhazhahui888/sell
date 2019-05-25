package com.huiwan.service;

import com.huiwan.bean.ProductInfo;
import com.huiwan.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * Created by mjl
 * 2017-05-09 17:27
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);

    //加库存
    void addStock(List<CartDTO> cartDTOList);

    //扣库存
    void deleteStock(List<CartDTO> cartDTOList);
}
