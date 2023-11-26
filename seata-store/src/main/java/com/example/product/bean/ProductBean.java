package com.example.product.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Data
@Getter
@Setter
@TableName("res_product_info")
public class ProductBean {

    @TableId
    private String productId;
    private String productName;
    private Integer storeNumber;

}
