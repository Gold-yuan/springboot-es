package com.tsdp.demo.controller;

import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsdp.demo.bean.FoodBusinessEnterprise;
import com.tsdp.demo.util.ESHighLevelRestUtil;

@RestController
@RequestMapping("/rest")
public class EsRestController {

    @Autowired
    private ESHighLevelRestUtil esClientUtil;

    @RequestMapping("/save")
    public String search() {
        String className = FoodBusinessEnterprise.class.getSimpleName()+"s";
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