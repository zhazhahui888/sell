package com.huiwan.respository;

import com.huiwan.bean.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/2/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRespositoryTest {

    @Autowired
    private ProductCategoryRepository respository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = respository.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll(){
        List<ProductCategory> productCategoryList = respository.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(4);
        productCategory.setCategoryName("酷暑饮品");
        productCategory.setCategoryType(4);
        ProductCategory category = respository.save(productCategory);
        Assert.assertNotEquals(null,category);
    }

}