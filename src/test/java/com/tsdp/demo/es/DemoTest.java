package com.tsdp.demo.es;

import java.util.List;

import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.tsdp.demo.bean.FoodBusinessEnterprise;
import com.tsdp.demo.dao.FoodBusinessEnterpriseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @Autowired
    private FoodBusinessEnterpriseRepository dao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void aaaa() {
        Iterable<FoodBusinessEnterprise> findAll = dao.findAll();
        for (FoodBusinessEnterprise foodBusinessEnterprise : findAll) {
            System.out.println(foodBusinessEnterprise);
        }
        List<FoodBusinessEnterprise> elasticSerchTest = elasticSerchTest();
        System.out.println(elasticSerchTest);
    }

    public List<FoodBusinessEnterprise> elasticSerchTest() {
        
        // 1.创建QueryBuilder(即设置查询条件)这儿创建的是组合查询(也叫多条件查询),后面会介绍更多的查询方法
//         组合查询BoolQueryBuilder
//              must(QueryBuilders)   :AND
//              mustNot(QueryBuilders):NOT
//              should:               :OR
//         
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        // builder下有must、should以及mustNot 相当于sql中的and、or以及not

        // 设置模糊搜索
//        builder.must(QueryBuilders.fuzzyQuery("enterpriseName", "北京"));
        builder.must(QueryBuilders.matchQuery("enterpriseName", "北京"));

        // 设置要查询的字段中含有关键字
//        builder.must(new QueryStringQueryBuilder("enterpriseNameEN").field("beijing"));
        
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(builder)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
       
        
        // 排序
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);

        // 设置分页(从第一页开始，一页显示10条)
        // 注意开始是从0开始
        PageRequest pageRequest = PageRequest.of(0, 10);

        // 2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        // 将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(pageRequest);
        // 将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        // 生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        // 3.执行方法1
        Page<FoodBusinessEnterprise> page2 = dao.search(query);
        // 执行方法2：注意，这儿执行的时候还有个方法那就是使用elasticsearchTemplate
        // 执行方法2的时候需要加上注解
        // @Autowired
        // private ElasticsearchTemplate elasticsearchTemplate;
//        List<FoodBusinessEnterprise> blogList = elasticsearchTemplate.queryForList(query, FoodBusinessEnterprise.class);
        List<Object> blogList = elasticsearchTemplate.queryForList(query, Object.class);

        System.out.println("elasticsearchTemplate: "+blogList);
        // 4.获取总条数(用于前端分页)
        int total = (int) page2.getTotalElements();

        // 5.获取查询到的数据内容（返回给前端）
        List<FoodBusinessEnterprise> content = page2.getContent();
        return content;
    }
}
