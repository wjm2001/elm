package org.example.application.service;

import jakarta.annotation.Resource;
import org.example.domain.model.Business;
import org.example.infrastructure.repository.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {
    @Resource
    private BusinessRepository businessRepository;

    public List<Business> findBusinessByOrderTypeId(Integer orderTypeId){
        return businessRepository.findBusinessByOrderTypeId(orderTypeId);
    }

    public Business findBusinessByBusinessId(Long businessId){
        return businessRepository.findBusinessByBusinessId(businessId);
    }
}
