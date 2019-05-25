package com.huiwan.respository;

import com.huiwan.bean.ProductInfo;
import com.huiwan.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/2/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRespositoryTest {

    @Autowired
    private ProductInfoRepository respository;


    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfoList = respository.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findOneTest(){
        ProductInfo productInfo = respository.findOne("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋瘦肉粥");
        productInfo.setProductPrice(new BigDecimal(8.8));
        productInfo.setProductDescription("味道鲜美，营养丰富");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setProductStock(88);
        productInfo.setCategoryType(3);
        productInfo.setProductIcon("http://xxxx/pidan.jpg");
        respository.save(productInfo);
    }
}