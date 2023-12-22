package com.ameda.kevin.data.repository;

import com.ameda.kevin.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o FROM Order o JOIN FETCH o.products WHERE o.oId= ?1")
    Optional<Order> findOrders(Long oId);
}
