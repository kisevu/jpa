package com.ameda.kevin.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String Category;
    private String address;
    private Integer reviews;
    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}
