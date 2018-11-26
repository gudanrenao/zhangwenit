package com.zhangwen.learn.zhangwenit.elasticsearch.service;

import com.zhangwen.learn.zhangwenit.elasticsearch.dao.MerchantInfoRepository;
import org.springframework.stereotype.Service;

/**
 * @Description es merchant test
 * @Author ZWen
 * @Date 2018/11/26 3:34 PM
 * @Version 1.0
 **/
@Service
public class MerchantEsService {

    private final MerchantInfoRepository merchantInfoRepository;

    public MerchantEsService(MerchantInfoRepository merchantInfoRepository) {
        this.merchantInfoRepository = merchantInfoRepository;
    }

    public void findById(Long id){

    }
}