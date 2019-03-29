package com.tsdp.demo.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.tsdp.demo.bean.FoodBusinessEnterprise;

@Component
public interface FoodBusinessEnterpriseRepository extends ElasticsearchRepository<FoodBusinessEnterprise, Long> {
    
}