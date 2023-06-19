package org.example.infrastructure.repository;

import org.example.domain.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
    public Food findByFoodId(Long foodId);
}
