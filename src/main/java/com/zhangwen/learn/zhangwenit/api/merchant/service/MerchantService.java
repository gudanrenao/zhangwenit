package com.zhangwen.learn.zhangwenit.api.merchant.service;

import com.zhangwen.learn.zhangwenit.api.merchant.dto.MerchantCriteria;
import com.zhangwen.learn.zhangwenit.api.merchant.entity.Merchant;
import com.zhangwen.learn.zhangwenit.api.merchant.repository.MerchantRepository;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.Date;

/**
 * @author zhangwen at 2018-08-15 21:18
 **/
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Page findAll(MerchantCriteria merchantCriteria, Integer limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Specification<Merchant> specification = (r, q, cb) -> {
            Predicate predicate = cb.conjunction();
            if (merchantCriteria.getPartnership() != null && merchantCriteria.getPartnership() != -1) {
                predicate.getExpressions().add(cb.equal(r.get("partnership"), merchantCriteria.getPartnership()));
            }
            if (!StringUtils.isEmpty(merchantCriteria.getMerchantName())) {
                predicate.getExpressions().add(cb.like(r.get("name"), "%" + merchantCriteria.getMerchantName() + "%"));
            }
            if (!StringUtils.isEmpty(merchantCriteria.getStart())) {
                try {
                    predicate.getExpressions().add(cb.between(r.get("createDate"),
                            DateUtils.parseDate(merchantCriteria.getStart(),"yyyy-MM-dd HH:mm:ss"),
                            DateUtils.parseDate(merchantCriteria.getEnd(),"yyyy-MM-dd HH:mm:ss")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return predicate;
        };
        return merchantRepository.findAll(specification, PageRequest.of(merchantCriteria.getCurrPage() - 1, limit, sort));
    }
}
