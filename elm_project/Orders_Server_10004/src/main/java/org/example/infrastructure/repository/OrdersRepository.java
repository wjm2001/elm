package org.example.infrastructure.repository;

import org.example.domain.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    public Orders findByOrdersId(Long ordersID);

    public List<Orders> findAllByUserId(Long userID);
}
