package com.ameda.kevin.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( name = "tbl_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long oId;
    private LocalDateTime placingTime;
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "o_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))

    private List<Product> products = new ArrayList<>();
}
