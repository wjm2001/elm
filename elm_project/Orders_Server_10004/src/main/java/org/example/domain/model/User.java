package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Integer sex;

    @Column
    private String userImg;

    @Column(insertable = false,columnDefinition = "int default 0")
    private Integer delTag;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Orders> orders;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<DeliveryAddress> deliveryAddresses;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Cart> carts;

    public Orders saveOrders(Business business, Double Total, DeliveryAddress deliveryAddress){
        Orders orders1 = new Orders();
        orders1.setUser(this);
        orders1.setBusiness(business);
        orders1.setTotal(Total);
        orders1.setDeliveryAddress(deliveryAddress);
        orders1.setState(0);
        orders.add(orders1);
        return orders1;
    }
}
