package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long cartId;

    @ManyToOne
    @JsonBackReference
    @JsonIgnoreProperties("carts")
    private Business business;

    @ManyToOne
    @JsonIgnoreProperties("carts")
    private Food food;

    @ManyToOne
    @JsonBackReference
    @JsonIgnoreProperties("carts")
    private User user;

    @Column
    private Integer quantity;
}
