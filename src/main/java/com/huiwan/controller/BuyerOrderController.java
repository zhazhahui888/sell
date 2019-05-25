package com.huiwan.controller;

import com.huiwan.bean.ProductCategory;
import com.huiwan.bean.ProductInfo;
import com.huiwan.converter.OrderForm2OrderDTO;
import com.huiwan.dto.OrderDTO;
import com.huiwan.enums.ResultEnum;
import com.huiwan.exception.SellException;
import com.huiwan.form.OrderForm;
import com.huiwan.service.CategoryService;
import com.huiwan.service.OrderService;
import com.huiwan.service.ProductService;
import com.huiwan.utils.MyUtils;
import com.huiwan.utils.ResultVOUtil;
import com.huiwan.vo.ProductInfoVo;
import com.huiwan.vo.ProductVo;
import com.huiwan.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/2/28.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private ProductService productInfoService;

    @Autowired
    private OrderService orderService;

    //创建订单接口
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult
                                               ){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm = {}" , orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if(MyUtils.isBlank(orderDTO.getOrderDetails())){
            log.error("【创建订单】购物车为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO orderResult = orderService.save(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (MyUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findByBuyerOpenId(openid, request);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }
}
