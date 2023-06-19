package org.example.application.feign;

import org.example.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("User-Server")
public interface FeignOrdersService {
    @GetMapping("user/findUserById")
    User findUserById(@RequestParam("userID") Long userID);
}
