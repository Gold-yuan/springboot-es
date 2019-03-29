package com.tsdp.demo.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.tsdp.demo.bean.DrugBusinessEnterprise;

@Component
public interface DrugBusinessEnterpriseRepository extends ElasticsearchRepository<DrugBusinessEnterprise, Long> {

}