package com.farmawebservice.delivery.user.model;

import com.farmawebservice.delivery.address.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "userx")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_auth_id")
    private Long userAuthId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> userAddresses;
}
