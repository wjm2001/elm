package org.example.infrastructure.repository;

import org.example.domain.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Long> {
    public List<DeliveryAddress> findAllByUserId(Long userId);

    public DeliveryAddress findByDaId(Long daId);
}
