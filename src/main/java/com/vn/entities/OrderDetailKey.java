package com.vn.entities;

//composite primary key

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "order_id")
    private Integer orderId;
}
