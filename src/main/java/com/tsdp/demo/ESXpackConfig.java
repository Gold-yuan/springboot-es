package com.tsdp.demo;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ESXpackConfig {
    @Bean    
    @Scope
    public TransportClient TransportClients() throws Exception {

        // 9300是es的tcp服务端口
        TransportAddress node = new TransportAddress(
                InetAddress.getByName("es-cn-45912tdyn001kbp1q.public.elasticsearch.aliyuncs.com"), 9300);

        // 设置es节点的配置信息
        Settings settings = Settings.builder().put("cluster.name", "es-cn-45912tdyn001kbp1q")
                .put("xpack.security.user", "elastic:TSDP_passw0rd").build();

        // 实例化es的客户端对象
//        PreBuiltXPackTransportClient client = new PreBuiltXPackTransportClient(settings);
        PreBuiltTransportClient client = new PreBuiltXPackTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
