package org.example.infrastructure.repository;

import org.example.domain.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {
    public List<Food> findAllByBusinessBusinessId(Long businessId);
}
