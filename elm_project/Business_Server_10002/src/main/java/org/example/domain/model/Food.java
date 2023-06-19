package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JsonIgnoreProperties("foods")
    private Business business;

    @Column
    private String remarks;
}

