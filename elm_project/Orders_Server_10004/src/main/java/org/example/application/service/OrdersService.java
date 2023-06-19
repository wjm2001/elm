package org.example.application.service;

import jakarta.annotation.Resource;
import org.example.domain.model.*;
import org.example.infrastructure.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
    @Resource
    private OrdersRepository ordersRepository;

    @Resource
    private CartRepository cartRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BusinessRepository businessRepository;

    @Resource
    private DeliveryAddressRepository deliveryAddressRepository;

    @Resource
    private OrdersDetailetRepository ordersDetailetRepository;

    public Orders saveOrders(Long userId, Long businessId, Double Total, Long daId){
        List<Cart> carts = cartRepository.findAllByUserIdAndBusinessBusinessId(userId, businessId);
        User user = userRepository.findUserById(userId);
        Business business = businessRepository.findByBusinessId(businessId);
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByDaId(daId);
        Orders orders = user.saveOrders(business,Total,deliveryAddress);
        ordersRepository.save(orders);
        userRepository.save(user);

        List<OrderDetailet> list = new ArrayList<>();
        for (Cart cart : carts){
            OrderDetailet orderDetailet = orders.saveOrderDetailet(cart.getFood(),cart.getQuantity());
            list.add(orderDetailet);
            ordersDetailetRepository.save(orderDetailet);
        }
        System.out.println(list);

        cartRepository.deleteAll(carts);
        return orders;
    }

    public Orders findByOrdersId(Long ordersID){
        return ordersRepository.findByOrdersId(ordersID);
    }

    public List<Orders> findAllByUserId(Long userID){
        return ordersRepository.findAllByUserId(userID);
    }
}
