package org.example.presentation;

import jakarta.annotation.Resource;
import org.example.application.service.BusinessService;
import org.example.domain.model.Business;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/business")
public class BusinessController {
    @Resource
    private BusinessService businessService;

    @GetMapping("/findBusinessByOrderTypeId")
    public List<Business> findBusinessByOrderTypeId(@RequestParam Integer orderTypeId) {
        return businessService.findBusinessByOrderTypeId(orderTypeId);
    }

    @GetMapping("/findBusinessByBusinessId")
    public Business findBusinessByBusinessId(Long businessId) {
        return businessService.findBusinessByBusinessId(businessId);
    }
}
