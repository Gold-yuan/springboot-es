package com.tsdp.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tsdp.demo.bean.DrugBusinessEnterprise;
import com.tsdp.demo.bean.FoodBusinessEnterprise;
import com.tsdp.demo.dao.DrugBusinessEnterpriseRepository;
import com.tsdp.demo.dao.FoodBusinessEnterpriseRepository;

@RestController
@RequestMapping("/")
public class FoodController {

    @Autowired
    private FoodBusinessEnterpriseRepository foodBEDao;
    @Autowired
    private DrugBusinessEnterpriseRepository drugBEDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/es")
    public String es() {
        String keyword = "苹果";
        String keyword2 = "禹丰达";
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery(keyword);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.should(queryBuilder);
        boolQuery.should(QueryBuilders.matchQuery("enterpriseName", keyword2));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                // 可以直接使用别名
                .withIndices("drugbusinessenterprise", "foodbusinessenterprise")
                .withQuery(boolQuery)
                // .withFields("enterpriseName")
                // .addAggregation(sumBuilder)
                .withPageable(PageRequest.of(0, 1111)).build();
        List<Map<String, Object>> map = elasticsearchTemplate.query(searchQuery, response -> {
            SearchHits hits = response.getHits();
            List<Map<String, Object>> list = new ArrayList<>();
            Arrays.stream(hits.getHits()).forEach(h -> {
                Map<String, Object> source = h.getSourceAsMap();
                source.put("_score", h.getScore());
                source.put("_index", h.getIndex());
                source.put("_type", h.getType());
                source.put("_id", h.getId());
                System.out.println(new Gson().toJson(source));
                list.add(source);
            });
            return list;
        });
        System.out.println(new Gson().toJson(map));
        System.out.println(map.size());
        return new Gson().toJson(map);
    }

    @RequestMapping("/saveFoodBE")
    public Integer saveFoodBE(@RequestBody List<FoodBusinessEnterprise> foods) {
        Random r = new Random();
        for (FoodBusinessEnterprise food : foods) {
            String id = System.currentTimeMillis() / 1000 + "" + r.nextInt(9000) + 1000;
            food.setId(Long.parseLong(id));
        }
        foodBEDao.saveAll(foods);
        return foods.size();
    }

    @RequestMapping("/saveDrugBE")
    public Integer saveDrugBE(@RequestBody List<DrugBusinessEnterprise> drugs) {
        Random r = new Random();
        for (DrugBusinessEnterprise drug : drugs) {
            String id = System.currentTimeMillis() / 1000 + "" + r.nextInt(9000) + 1000;
            drug.setId(Long.parseLong(id));
        }
        drugBEDao.saveAll(drugs);
        return drugs.size();
    }

    // http://localhost:8888/save
    @GetMapping("/save")
    public FoodBusinessEnterprise save() {
        FoodBusinessEnterprise food = new FoodBusinessEnterprise();
        food.setId(System.currentTimeMillis());
        food.setBusinessAddress("天津市");
        food.setBusinessScope("辣片");
        food.setEnterpriseName("天津清华智信");
        food.setEnterpriseNameEN("beijingThinghuaZhixin");
        food.setExpirationDate("2222-02-22");
        food.setMainBusinessFormat("卖食物");
        food.setStatus("有效");
        food.setSocialCreditCode("sc215987611576126562");
        food.setLicenseNumber("dfs554548745131654984");
        foodBEDao.save(food);
        return food;
    }

    // http://localhost:8888/delete?id=1525415333329
    @GetMapping("/delete")
    public String delete(long id) {
        foodBEDao.deleteById(id);
        return "success";
    }

    // http://localhost:8888/update?id=1525417362754&name=修改&description=修改
    @GetMapping("/update")
    public FoodBusinessEnterprise update(long id, String enterpriseName) {
        FoodBusinessEnterprise food = new FoodBusinessEnterprise();
        food.setEnterpriseName(enterpriseName);
        food.setId(id);
        foodBEDao.save(food);
        return food;
    }

    // http://localhost:8888/getOne?id=1525417362754
    @GetMapping("/getOne")
    public FoodBusinessEnterprise getOne(long id) {
        Optional<FoodBusinessEnterprise> food = foodBEDao.findById(id);
        if (food.isPresent()) {
            return food.get();
        } else {
            return null;
        }
    }

    // 每页数量
    private Integer PAGESIZE = 10;

    // http://localhost:8888/getGoodsList?query=商品
    // http://localhost:8888/getGoodsList?query=商品&pageNumber=1
    // 根据关键字"商品"去查询列表，name或者description包含的都查询
    @GetMapping("/getGoodsList")
    public List<FoodBusinessEnterprise> getList(Integer pageNumber, String query) {
        if (pageNumber == null)
            pageNumber = 0;
        // es搜索默认第一页页码是0
        SearchQuery searchQuery = getEntitySearchQuery(pageNumber, PAGESIZE, query);
        Page<FoodBusinessEnterprise> goodsPage = foodBEDao.search(searchQuery);
        return goodsPage.getContent();
    }

    private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery(); // 搜索 title字段包含IPhone的数据
        queryBuilder.must(QueryBuilders.matchQuery("enterpriseName", searchContent));
        FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(queryBuilder)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
        // 设置分页
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new NativeSearchQueryBuilder().withPageable(pageable).withQuery(query).build();
    }

}