package org.example.presentation;

import jakarta.annotation.Resource;
import org.example.application.service.FoodService;
import org.example.domain.model.Food;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/food")
public class FoodController {
    @Resource
    private FoodService foodService;

    @GetMapping("/findFoodsByBusinessId")
    public List<Food> findAllByBusinessId(@RequestParam Long businessId){
        return foodService.findAllByBusinessId(businessId);
    }
}
