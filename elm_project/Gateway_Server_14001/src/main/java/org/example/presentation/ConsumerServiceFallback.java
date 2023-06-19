package org.example.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerServiceFallback {
    @RequestMapping(value = "/fallback",method = RequestMethod.GET)
    public String GetCommonResult(){
        return "由于ConsumerService异常进行服务器降级";
    }
}
