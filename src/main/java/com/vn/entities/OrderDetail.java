package com.vn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    @EmbeddedId
    private OrderDetailKey id;

    private Integer quantity;

    @Column(name = "price_actual")
    private Double priceActual;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @MapsId(value = "productId")
    private Product product;

    @ManyToOne
    @MapsId(value = "orderId")
    private Order order;

}
