package com.vn.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;

    @Column(precision = 18,scale = 3)
    private BigDecimal price;

    private String image;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;

}
