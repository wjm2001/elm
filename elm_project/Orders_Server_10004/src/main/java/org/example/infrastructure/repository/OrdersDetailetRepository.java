package org.example.infrastructure.repository;

import org.example.domain.model.OrderDetailet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailetRepository extends JpaRepository<OrderDetailet,Long> {
}
