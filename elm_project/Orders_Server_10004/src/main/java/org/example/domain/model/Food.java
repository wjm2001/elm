package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long foodId;

    @Column
    private String foodName;

    @Column
    private String foodExplain;

    @Column
    private String foodImg;

    @Column
    private Double foodPrice;

    @Column
    private String remarks;

    @OneToMany(mappedBy = "food")
    @JsonIgnoreProperties("food")
    private List<OrderDetailet> orderDetailets;

    @OneToMany(mappedBy = "food")
    @JsonIgnoreProperties("food")
    private List<Cart> carts;
}

