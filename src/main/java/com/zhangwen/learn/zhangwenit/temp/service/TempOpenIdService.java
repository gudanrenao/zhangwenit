package com.zhangwen.learn.zhangwenit.temp.service;

import com.zhangwen.learn.zhangwenit.temp.entities.TempOpenId;
import com.zhangwen.learn.zhangwenit.temp.repository.TempOpenIdRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/10 1:27 PM
 * @Version 1.0
 **/
@Service
public class TempOpenIdService {

    private final TempOpenIdRepository tempOpenIdRepository;

    public TempOpenIdService(TempOpenIdRepository tempOpenIdRepository) {
        this.tempOpenIdRepository = tempOpenIdRepository;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(String openId) {
//        TempOpenId tempOpenId = new TempOpenId();
//        tempOpenId.setOpenId(openId);
//        tempOpenIdRepository.saveAndFlush(tempOpenId);
        List<TempOpenId> list = tempOpenIdRepository.findAllByOpenId(openId);
        if (list == null || list.size() == 0) {
            TempOpenId tempOpenId = new TempOpenId();
            tempOpenId.setOpenId(openId);
            tempOpenIdRepository.saveAndFlush(tempOpenId);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void insertAll(List<TempOpenId> list) {
        tempOpenIdRepository.saveAll(list);
        tempOpenIdRepository.flush();
    }
}