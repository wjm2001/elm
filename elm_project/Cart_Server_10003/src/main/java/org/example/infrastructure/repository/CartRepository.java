package org.example.infrastructure.repository;

import org.example.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    public List<Cart> findAllByUserId(Long userid);

    public List<Cart> findAllByUserIdAndBusinessBusinessId(Long userId,Long businessId);

    public List<Cart> findByUserIdAndBusinessBusinessIdAndFoodFoodId(Long userId,Long businessId,Long foodId);
}
