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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
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
public class EsController {

    @Autowired
    private FoodBusinessEnterpriseRepository foodBEDao;
    @Autowired
    private DrugBusinessEnterpriseRepository drugBEDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/search")
    public String search() {
        String keyWorld = "同仁堂";
        
        String licenseNumber = "晋AB3590125-?";
        String province = "山西省";
        String type = "食品";
        String validityStart = "";
        String validityEnd = "";
        String issuingDateStart = "";
        String issuingDateEnd = "";
        
        List<Map<String, Object>> map = null;
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 范围（从数据库取数据）
        List<String> indices = new ArrayList<String>();
        if (type.equals("食品")) {
            indices.add("drugbusinessenterprise");
            indices.add("foodbusinessenterprise");
        }
        
        // 省份
        if (StringUtils.isNotBlank(province)) {
            //boolQuery.must(QueryBuilders.matchQuery("province", province));
        }
        // 备案许可号
        if (StringUtils.isNotBlank(licenseNumber)) {
//            boolQuery.must(QueryBuilders.queryStringQuery(licenseNumber).field("licenseNumber"));
//            boolQuery.must(QueryBuilders.termQuery("licenseNumber", licenseNumber));
//            boolQuery.must(QueryBuilders.fuzzyQuery("licenseNumber", licenseNumber));
//            boolQuery.must(QueryBuilders.wildcardQuery("licenseNumber", licenseNumber));
        }
        // 有效期起
        if (StringUtils.isNotBlank(validityStart)) {
//            boolQuery.must(QueryBuilders.rangeQuery("expirationDate").gte(validityStart));
        }
        // 有效期至
        if (StringUtils.isNotBlank(validityEnd)) {
//            boolQuery.must(QueryBuilders.rangeQuery("expirationDate").lte(validityEnd));
        }
        
        // 主页搜索
        if (StringUtils.isNotBlank(keyWorld)) {
            boolQuery.must(QueryBuilders.queryStringQuery(keyWorld));
        }
        
        
        PageRequest pageRequest = PageRequest.of(0, 100);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices(indices.toArray(new String[] {}))
                .withQuery(boolQuery).withPageable(pageRequest).build();
        
        map = query(searchQuery);
        

        String json = new Gson().toJson(map);
        return json;
    }

    @RequestMapping("/searchAl")
    public String searchAl() {
        String licenseNumber = "晋AA3510144";
//        licenseNumber = "晋AA351014";
        List<DrugBusinessEnterprise> content = null;
        if (StringUtils.isNotBlank(licenseNumber)) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//            boolQuery.must(QueryBuilders.termQuery("licenseNumber", licenseNumber));
//            boolQuery.must(QueryBuilders.matchQuery("licenseNumber", licenseNumber));
            boolQuery.must(QueryBuilders.fuzzyQuery("licenseNumber", licenseNumber));
//            boolQuery.must(QueryBuilders.queryStringQuery(licenseNumber).field("licenseNumber"));
            
            PageRequest pageRequest = PageRequest.of(0, 100);
            // 2.构建查询
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            // 将搜索条件设置到构建中
            nativeSearchQueryBuilder.withQuery(boolQuery);
            // 将分页设置到构建中
            nativeSearchQueryBuilder.withPageable(pageRequest);
            // 将排序设置到构建中
            // nativeSearchQueryBuilder.withSort(sort);
            // 生产NativeSearchQuery
            NativeSearchQuery query = nativeSearchQueryBuilder.build();
            Page<DrugBusinessEnterprise> search = drugBEDao.search(query);
            content = search.getContent();
            
        }
        String json = new Gson().toJson(content);
        return json;
    }

    private List<Map<String, Object>> query(SearchQuery query) {
        List<Map<String, Object>> map = elasticsearchTemplate.query(query, response -> {
            SearchHits hits = response.getHits();
            List<Map<String, Object>> list = new ArrayList<>();
            Arrays.stream(hits.getHits()).forEach(h -> {
                Map<String, Object> source = h.getSourceAsMap();
                source.put("_score", h.getScore());
                source.put("_index", h.getIndex());
                source.put("_type", h.getType());
                source.put("_id", h.getId());
                list.add(source);
            });
            return list;
        });
        return map;
    }

    @RequestMapping("/es")
    public String es() {
        String keyword = "晋AA351014";
        String keyword2 = "禹丰达";
        String province = "北京市";
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery(keyword);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.should(queryBuilder);
//        boolQuery.should(QueryBuilders.matchQuery("enterpriseName", keyword2));
//        boolQuery.should(QueryBuilders.matchQuery("province", province));

        String[] indices = { "drugbusinessenterprise", "foodbusinessenterprise" };
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                // 可以直接使用别名
                .withIndices(indices).withQuery(boolQuery)
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