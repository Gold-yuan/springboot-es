package com.tsdp.demo;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.context.annotation.Bean;

public class ESXpackConfig {
    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
                .put("cluster.name", "es-cluster")
                .put("xpack.security.user", "elastic:TSDP_passw0rd")
                .build())
                .addTransportAddress(new TransportAddress(new InetSocketAddress(InetAddress.getByName("es-cn-45912tdyn001kbp1q.public.elasticsearch.aliyuncs.com"), 9300)));
        return client;
        }
}
