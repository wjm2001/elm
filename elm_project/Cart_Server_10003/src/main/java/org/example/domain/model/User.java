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

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Cart> carts;

    @Column(insertable = false,columnDefinition = "int default 0")
    private Integer delTag;

    public Cart saveCart(Business business,Food food){
        Cart cart = new Cart();
        cart.setBusiness(business);
        cart.setFood(food);
        cart.setUser(this);
        cart.setQuantity(1);
        carts.add(cart);
        return cart;
    }
}
