package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long daId;

    @Column(nullable = false)
    private String contactName;

    @Column(nullable = false)
    private String contactSex;

    @Column(nullable = false)
    private String contactTel;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "deliveryAddress")
    @JsonIgnoreProperties("deliveryAddress")
    private List<Orders> orders;

    @ManyToOne
    @JsonBackReference
    @JsonIgnoreProperties("deliveryAddresses")
    private User user;
}
