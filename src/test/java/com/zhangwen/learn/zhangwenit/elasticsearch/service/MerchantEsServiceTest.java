package com.zhangwen.learn.zhangwenit.elasticsearch.service;

import com.zhangwen.learn.zhangwenit.ZhangwenitApplication;
import com.zhangwen.learn.zhangwenit.api.merchant.entity.Merchant;
import com.zhangwen.learn.zhangwenit.api.merchant.repository.MerchantRepository;
import com.zhangwen.learn.zhangwenit.elasticsearch.dao.MerchantInfoRepository;
import com.zhangwen.learn.zhangwenit.elasticsearch.model.MerchantInfo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ZhangwenitApplication.class)
@RunWith(SpringRunner.class)
public class MerchantEsServiceTest {

    @Autowired
    private MerchantInfoRepository merchantInfoRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    /**
     * 添加merchant数据到es
     */
    @Test
    public void addAllMerchantToEs() {
        List<Merchant> merchantList = merchantRepository.findAll();
        List<MerchantInfo> resultList = new ArrayList<>();
        for (Merchant m : merchantList) {
            MerchantInfo merchantInfo = new MerchantInfo();
            BeanUtils.copyProperties(m, merchantInfo);
            resultList.add(merchantInfo);
        }
        merchantInfoRepository.saveAll(resultList);
    }

    @Test
    public void queryTest() {

        PageRequest pageRequest = PageRequest.of(0, 100, Sort.Direction.DESC, "id");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.termQuery("name","测试门店002"));
        Page<MerchantInfo> page = merchantInfoRepository.search(boolQueryBuilder, pageRequest);
        System.out.println(page);
    }
}