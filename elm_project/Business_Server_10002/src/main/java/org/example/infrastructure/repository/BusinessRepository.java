package org.example.infrastructure.repository;

import org.example.domain.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business,Long> {
    public List<Business> findBusinessByOrderTypeId(Integer orderTypeId);

    public Business findBusinessByBusinessId(Long businessId);
}
