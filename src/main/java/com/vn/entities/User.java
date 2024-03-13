package com.vn.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    private String image;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private UserDetail userDetail;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User(Integer userId, String name, String image) {
        this.userId = userId;
        this.name = name;
        this.image = image;
    }
}
