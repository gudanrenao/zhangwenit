//package com.zhangwen.learn.zhangwenit.elasticsearch.service;
//
//import com.zhangwen.learn.zhangwenit.ZhangwenitApplication;
//import com.zhangwen.learn.zhangwenit.api.merchant.entity.Merchant;
//import com.zhangwen.learn.zhangwenit.api.merchant.repository.MerchantRepository;
//import com.zhangwen.learn.zhangwenit.elasticsearch.dao.MerchantInfoRepository;
//import com.zhangwen.learn.zhangwenit.elasticsearch.model.MerchantInfo;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest(classes = ZhangwenitApplication.class)
//@RunWith(SpringRunner.class)
//public class MerchantEsServiceTest {
//
//    @Autowired
//    private MerchantInfoRepository merchantInfoRepository;
//
//    @Autowired
//    private MerchantRepository merchantRepository;
//
//    /**
//     * 添加merchant数据到es
//     */
//    @Test
//    public void addAllMerchantToEs() {
//        List<Merchant> merchantList = merchantRepository.findAll();
//        List<MerchantInfo> resultList = new ArrayList<>();
//        for (Merchant m : merchantList) {
//            MerchantInfo merchantInfo = new MerchantInfo();
//            BeanUtils.copyProperties(m, merchantInfo);
//            resultList.add(merchantInfo);
//        }
//        merchantInfoRepository.saveAll(resultList);
//    }
//
//    @Test
//    public void queryTest() {
//
//        PageRequest pageRequest = PageRequest.of(0, 100, Sort.Direction.DESC, "id");
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //模糊查询
//        boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("name","测试"));
//        //大于
//        boolQueryBuilder.must(QueryBuilders.rangeQuery("id").gt(1000));
//        //嵌套
////        boolQueryBuilder.must(QueryBuilders.nestedQuery("merchantUser"),QueryBuilders.matchPhraseQuery("merchantName","ceshi"));
//
//        //过滤器，不影响评分_score
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("id").lt(1300));
//
//
//        Page<MerchantInfo> page = merchantInfoRepository.search(boolQueryBuilder, pageRequest);
//        System.out.println("总数为：" + page.getTotalElements());
//        page.forEach(e -> System.out.println(e.getId() + "----" + e.getName() + "----" + String.valueOf(e.getMerchantUser() == null ? "null" : e.getMerchantUser().getMerchantName())));
//    }
//}