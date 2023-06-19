package org.example.presentation;

import jakarta.annotation.Resource;
import org.example.domain.model.Cart;
import org.example.application.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    @GetMapping("/listCart")
    public List<Cart> listCart(@RequestParam Long userId,@RequestParam(required = false) Long businessId){
        return cartService.findAllByUserId_BusinessId(userId,businessId);
    }

    @PostMapping("/saveCart")
    public Cart saveCart(@RequestParam Long businessId,@RequestParam Long foodId,@RequestParam Long userId){
        return cartService.saveCart(businessId, foodId, userId);
    }

    @PostMapping("/updateCart")
    public List<Cart> updateCart(@RequestParam Long userId,
                           @RequestParam Long businessId,
                           @RequestParam Long foodId,
                           @RequestParam Integer quantity){
        return cartService.updateCart(userId, businessId, foodId, quantity);
    }

    @PostMapping("/removeCart")
    public void removeCart(@RequestParam Long userId,
                                 @RequestParam Long businessId,
                                 @RequestParam Long foodId){
        cartService.removeCart(userId, businessId, foodId);
    }
}
