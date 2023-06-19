package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long ordersId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String Date;

    @Column
    private Double Total;

    @Column
    private Integer State; //订单状态（0：未支付； 1：已支付）

    @ManyToOne
    @JsonBackReference(value = "user")
    @JsonIgnoreProperties("orders")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Business business;

    @ManyToOne
    @JsonBackReference(value = "deliveryAddress")
    @JsonIgnoreProperties("orders")
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy = "orders")
    @JsonIgnoreProperties("orders")
    private List<OrderDetailet> orderDetailets;

    public OrderDetailet saveOrderDetailet(Food food,Integer quantity){
        List<OrderDetailet> list = new ArrayList<>();
        OrderDetailet orderDetailet = new OrderDetailet();
        orderDetailet.setOrders(this);
        orderDetailet.setFood(food);
        orderDetailet.setQuantity(quantity);
        list.add(orderDetailet);
        orderDetailets = new ArrayList<>(list);
        return orderDetailet;
    }
}
