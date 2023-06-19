package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long businessId;

    @Column
    private String businessName;

    @Column
    private String businessAddress;

    @Column
    private String businessExplain;

    @Column
    private String businessImg;

    @Column
    private Integer orderTypeId;

    @Column
    private double starPrice; //起送费

    @Column
    private double deliveryPrice; //配送费

    @Column
    private String remarks;

    @OneToMany(mappedBy = "business")
    @JsonIgnoreProperties("business")
    private List<Food> foods;

    @OneToMany(mappedBy = "business")
    @JsonIgnoreProperties("business")
    private List<Cart> carts;
}

