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
    private List<DeliveryAddress> deliveryAddresses;

    public DeliveryAddress saveDeliveryAddress(String contactName, String contactSex, String contactTel, String address){
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(contactName);
        deliveryAddress.setContactSex(contactSex);
        deliveryAddress.setContactTel(contactTel);
        deliveryAddress.setAddress(address);
        deliveryAddress.setUser(this);
        deliveryAddresses.add(deliveryAddress);
        return deliveryAddress;
    }
}
