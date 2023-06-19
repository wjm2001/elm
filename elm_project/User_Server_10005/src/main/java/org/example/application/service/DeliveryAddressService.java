package org.example.application.service;

import jakarta.annotation.Resource;
import org.example.domain.model.DeliveryAddress;
import org.example.domain.model.User;
import org.example.infrastructure.repository.DeliveryAddressRepository;
import org.example.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {
    @Resource
    private DeliveryAddressRepository deliveryAddressRepository;

    @Resource
    private UserRepository userRepository;

    public List<DeliveryAddress> findAllByUserId(Long userId){
        return deliveryAddressRepository.findAllByUserId(userId);
    }

    public DeliveryAddress findByDaId(Long daId){
        return deliveryAddressRepository.findByDaId(daId);
    }

    public DeliveryAddress saveDeliveryAddress(String contactName,String contactSex,String contactTel,String address,Long userId) {
        User user = userRepository.findUserById(userId);
        DeliveryAddress deliveryAddress = user.saveDeliveryAddress(contactName, contactSex, contactTel, address);
        deliveryAddressRepository.save(deliveryAddress);
        userRepository.save(user);
        return deliveryAddress;
    }
    public DeliveryAddress updateDeliveryAddress(String contactName,String contactSex,String contactTel,String address,Long daId){
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByDaId(daId);
        deliveryAddress.setContactName(contactName);
        deliveryAddress.setContactSex(contactSex);
        deliveryAddress.setContactTel(contactTel);
        deliveryAddress.setAddress(address);
        deliveryAddressRepository.save(deliveryAddress);
        return deliveryAddress;
    }

    public void removeDeliveryAddress(Long daId){
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByDaId(daId);
        deliveryAddressRepository.delete(deliveryAddress);
    }
}
