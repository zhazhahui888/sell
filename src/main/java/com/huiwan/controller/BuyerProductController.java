package com.huiwan.controller;

import com.huiwan.bean.ProductCategory;
import com.huiwan.bean.ProductInfo;
import com.huiwan.service.CategoryService;
import com.huiwan.service.ProductService;
import com.huiwan.vo.ProductInfoVo;
import com.huiwan.vo.ProductVo;
import com.huiwan.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/2/28.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productInfoService;

    @Autowired
    private CategoryService categoryService;
    /**
     * @Author mjl
     * @Description // 商品列表
     * @Date 9:26 2019/5/24
     * @Param []
     * @return com.huiwan.vo.ResultVo
     **/
    @GetMapping("/list")
    public ResultVo list(){
        ResultVo resultVo = new ResultVo();
        //查询所有在架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //查询类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼装
        List<ProductVo> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVo productVO = new ProductVo();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                    if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVO = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVoList(productInfoVOList);
            productVOList.add(productVO);
        }
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        resultVo.setData(productVOList);
        return resultVo;
    }
}
