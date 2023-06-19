package org.example.presentation;

import jakarta.annotation.Resource;
import org.example.application.feign.FeignOrdersService;
import org.example.application.loadBanlanced.LoadBalanceConfiguration;
import org.example.application.service.OrdersService;
import org.example.domain.model.Orders;
import org.example.domain.model.User;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
@LoadBalancerClient(name = "User-Server",configuration = LoadBalanceConfiguration.class)
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    @Resource
    private FeignOrdersService feignOrdersService;

    @GetMapping("/findUserById")
    public User findUserById(@RequestParam Long userID){
        return feignOrdersService.findUserById(userID);
    }

    @PostMapping("/saveOrders")
    public Orders saveOrders(@RequestParam Long userId,
                             @RequestParam Long businessId,
                             @RequestParam Double Total,
                             @RequestParam Long daId){
        return ordersService.saveOrders(userId, businessId, Total, daId);
    }

    @GetMapping("/findByOrdersId")
    public Orders findByOrdersId(@RequestParam Long ordersId){
        return ordersService.findByOrdersId(ordersId);
    }

    @GetMapping("/findAllByUserId")
    public List<Orders> findAllByUserId(@RequestParam Long userID){
        return ordersService.findAllByUserId(userID);
    }
}
