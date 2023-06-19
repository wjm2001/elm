package org.example.application.service;

import jakarta.annotation.Resource;
import org.example.infrastructure.repository.BusinessRepository;
import org.example.infrastructure.repository.CartRepository;
import org.example.infrastructure.repository.FoodRepository;
import org.example.infrastructure.repository.UserRepository;
import org.example.domain.model.Business;
import org.example.domain.model.Cart;
import org.example.domain.model.Food;
import org.example.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Resource
    private CartRepository cartRepository;

    @Resource
    private BusinessRepository businessRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private FoodRepository foodRepository;

    public List<Cart> findAllByUserId_BusinessId(Long userId,Long businessId){
        if(businessId==null){
            return cartRepository.findAllByUserId(userId);
        }
        return cartRepository.findAllByUserIdAndBusinessBusinessId(userId,businessId);
    }

    public Cart saveCart(Long businessId,Long foodId,Long userId){
        Business business = businessRepository.findByBusinessId(businessId);
        Food food = foodRepository.findByFoodId(foodId);
        User user = userRepository.findUserById(userId);
        Cart cart = user.saveCart(business,food);
        cartRepository.save(cart);
        userRepository.save(user);
        return cart;
    }

    public List<Cart> updateCart(Long userId,Long businessId,Long foodId,Integer quantity){
        List<Cart> carts = cartRepository.findByUserIdAndBusinessBusinessIdAndFoodFoodId(userId, businessId, foodId);
        for (Cart cart : carts){
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
        return carts;
    }

    public void removeCart(Long userId,Long businessId,Long foodId){
        List<Cart> carts = new ArrayList<Cart>();
        if (foodId==null){
            carts = cartRepository.findAllByUserIdAndBusinessBusinessId(userId, businessId);
            cartRepository.deleteAll(carts);
        }
        carts = cartRepository.findByUserIdAndBusinessBusinessIdAndFoodFoodId(userId, businessId, foodId);
        cartRepository.deleteAll(carts);
    }
}
