package com.ameda.kevin.data.DTO;

import com.ameda.kevin.data.entity.Order;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
public class ProductNameAndOrders {
    private final String productName;
    private Order order;
    public ProductNameAndOrders(String productName, Order order) {
        this.productName = productName;
        this.order = order;
    }
}
