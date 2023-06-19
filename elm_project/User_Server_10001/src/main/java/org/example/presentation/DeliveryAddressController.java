package org.example.presentation;

import jakarta.annotation.Resource;
import org.example.domain.model.DeliveryAddress;
import org.example.application.service.DeliveryAddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/deliveryAddress")
public class DeliveryAddressController {
    @Resource
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("/findAllByUserId")
    public List<DeliveryAddress> findAllByUserId(@RequestParam Long userId){
        return deliveryAddressService.findAllByUserId(userId);
    }

    @GetMapping("/findByDaId")
    public DeliveryAddress findByDaId(Long daId){
        return deliveryAddressService.findByDaId(daId);
    }

    @PostMapping("/saveDeliveryAddress")
    public DeliveryAddress saveDeliveryAddress(@RequestParam String contactName,
                                               @RequestParam String contactSex,
                                               @RequestParam String contactTel,
                                               @RequestParam String address,
                                               @RequestParam Long userId){
        return deliveryAddressService.saveDeliveryAddress(contactName, contactSex, contactTel, address, userId);
    }

    @PostMapping("/updateDeliveryAddress")
    public DeliveryAddress updateDeliveryAddress(@RequestParam String contactName,
                                                 @RequestParam String contactSex,
                                                 @RequestParam String contactTel,
                                                 @RequestParam String address,
                                                 @RequestParam Long daId){
        return deliveryAddressService.updateDeliveryAddress(contactName, contactSex, contactTel, address, daId);
    }

    @PostMapping("/removeDeliveryAddress")
    public void removeDeliveryAddress(@RequestParam Long daId){
        deliveryAddressService.removeDeliveryAddress(daId);
    }
}
