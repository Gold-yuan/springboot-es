package com.tsdp.demo.es;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.tsdp.demo.bean.FoodBusinessEnterprise;
import com.tsdp.demo.dao.FoodBusinessEnterpriseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoNestedTest {

    @Autowired
    private FoodBusinessEnterpriseRepository dao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void aaaa() {
        System.out.println(DemoNestedTest.class.getName());
        elasticSerchTest();
    }

    public List<FoodBusinessEnterprise> elasticSerchTest() {

        MatchAllQueryBuilder matchAll = QueryBuilders.matchAllQuery();
        matchAll.queryName("药材");
        
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                // 可以直接使用别名
                .withIndices("licensenumbertwo", "licensenumber")
                .withFilter(QueryBuilders.existsQuery("enterpriseName"))
                 .withQuery(matchAll)
                // .addAggregation(sumBuilder)
                .withPageable(PageRequest.of(0, 1111)).build();
        List<Map<String, Object>> map = elasticsearchTemplate.query(searchQuery, response -> {
            SearchHits hits = response.getHits();
            List<Map<String, Object>> list = new ArrayList<>();
            Arrays.stream(hits.getHits()).forEach(h -> {
                Map<String, Object> source = h.getSourceAsMap();
                source.put("_index", h.getIndex());
                source.put("_type", h.getType());
                System.out.println(new Gson().toJson(source));
                list.add(source);
            });
            return list;
        });
        System.out.println(new Gson().toJson(map));
        System.out.println(map.size());
        return null;
    }
}
