package com.ameda.kevin.data.controller;

import com.ameda.kevin.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final ProductService productService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestParam("productId") Long productId){
        return new ResponseEntity<>(
                productService.placeOrder(productId)
                ,HttpStatus.CREATED);
    }
    @GetMapping("/{oId}")
    public ResponseEntity<?> getOrdersById(@PathVariable("oId") Long oId){
        return new ResponseEntity<>(
                productService.getOrders(oId)
                ,HttpStatus.OK);
    }
}
