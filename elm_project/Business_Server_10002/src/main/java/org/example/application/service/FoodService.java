package org.example.application.service;

import jakarta.annotation.Resource;
import org.example.domain.model.Food;
import org.example.infrastructure.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Resource
    private FoodRepository foodRepository;

    public List<Food> findAllByBusinessId(Long businessId){
        return foodRepository.findAllByBusinessBusinessId(businessId);
    }
}
