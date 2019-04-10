package com.tsdp.demo.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tsdp.demo.bean.FoodBusinessEnterprise;
import com.tsdp.demo.util.ESHighLevelRestUtil;

@RestController
@RequestMapping("/rest")
public class EsRestController {

    @Autowired
    private ESHighLevelRestUtil esClientUtil;

    @RequestMapping("/createIndex")
    public String createIndex() {
        String className = FoodBusinessEnterprise.class.getSimpleName() + "s";
        try {
            Resource res = new ClassPathResource("es-mapping/" + className.toLowerCase() + ".json");
            // ② 将文件内容拷贝到一个 String 中
            String mapping = FileCopyUtils.copyToString(new FileReader(res.getFile()));
            esClientUtil.indexCreate(className.toLowerCase(), className, mapping);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @RequestMapping("/insertDocument")
    public String insertDocument() {
        FoodBusinessEnterprise food = new FoodBusinessEnterprise();
        food.setEnterpriseName("北京");
        String className = FoodBusinessEnterprise.class.getSimpleName() + "s";
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();
        map.put(ESHighLevelRestUtil.INDEX_KEY, className.toLowerCase());
        map.put(ESHighLevelRestUtil.TYPE_KEY, className);
        map.put(ESHighLevelRestUtil.ID, System.currentTimeMillis() + "" + new Random().nextInt(10000));
        map.put(ESHighLevelRestUtil.DATA, new Gson().toJson(food));
        list.add(map);
        try {
            esClientUtil.bulkAdd(list);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "ok";
    }

    @RequestMapping("/delete")
    public String delete(String idnexName) {
        try {
            esClientUtil.deleteIndex(idnexName);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "ok";
    }
}