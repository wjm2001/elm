package org.example.infrastructure.repository;

import org.example.domain.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business,Long> {
    public Business findByBusinessId(Long businessId);
}
