package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDetailet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long odId;

    @Column
    private Integer quantity;

    @ManyToOne
    @JsonBackReference
    @JsonIgnoreProperties("orderDetailets")
    private Orders orders;

    @ManyToOne
    @JsonIgnoreProperties("orderDetailets")
    private Food food;
}
