package com.example.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.product.bean.ProductBean;
import com.example.product.mapper.ResProductInfoMapper;
import com.example.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ResProductInfoMapper productInfoMapper;

    @Override
    public void reduceProductById(String id) {
        ProductBean productBean = new ProductBean();
        productBean.setProductId(id);
        productBean.setStoreNumber(68);
        productInfoMapper.updateById(productBean);

        /*        UpdateWrapper<ProductBean> updateWrapper = productInfoMapper.
        productInfoMapper.update(productBean)*/

    }
}
