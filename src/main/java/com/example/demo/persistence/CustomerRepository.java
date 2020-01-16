package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE :id = o.id ")
    Customer findByOrder(Long id);
    /*
    @Query("SELECT 'orders' FROM Customer c WHERE :customerId = c.customerId ")
    List<Order> findOrdersByCustomerId(Long customerId);
     */
}
