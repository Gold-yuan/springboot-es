package com.tsdp.demo;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESXpackConfig {
    @Bean
    public TransportClient TransportClients() throws Exception {

        // 9300是es的tcp服务端口
        TransportAddress node = new TransportAddress(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 9300));

        // 设置es节点的配置信息
        Settings settings = Settings.builder().put("cluster.name", "es-5.0-test")
                .put("xpack.security.user", "elastic:TSDP_passw0rd").build();

        // 实例化es的客户端对象
        return new PreBuiltXPackTransportClient(settings).addTransportAddress(node);
    }
}
