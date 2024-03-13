package com.vn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Users_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetail implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    private String phone;

    @Column(name = "cmnd_front")
    private String cmndFront;

    @Column(name = "cmnd_back")
    private String cmndBack;

    @Column(name = "birth_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDay;

    @OneToOne
    @MapsId
    private User users;
}
