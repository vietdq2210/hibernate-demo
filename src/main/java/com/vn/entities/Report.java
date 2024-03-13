package com.vn.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Report {
    @Id
    private Integer userId;
    private String fullName;
    private Long countOrder;
    private Double totalAmount;

}
