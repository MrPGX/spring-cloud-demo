package com.example.order.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
@Data
@TableName("res_order_info")
public class OrderBean {

    private String orderId;
    private String orderName;
    private Date interTime;

}
