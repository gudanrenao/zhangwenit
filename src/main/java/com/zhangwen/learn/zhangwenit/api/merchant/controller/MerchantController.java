package com.zhangwen.learn.zhangwenit.api.merchant.controller;

import com.zhangwen.learn.zhangwenit.api.merchant.dto.MerchantCriteria;
import com.zhangwen.learn.zhangwenit.api.merchant.service.MerchantService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwen at 2018-08-15 21:36
 **/
@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping(value = "/list")
    public Page getMerchants(MerchantCriteria merchantCriteria) {
        return merchantService.findAll(merchantCriteria, merchantCriteria.getPageSize());
    }

}
