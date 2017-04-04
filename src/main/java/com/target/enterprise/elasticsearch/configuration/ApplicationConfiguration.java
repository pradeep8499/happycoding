package com.target.enterprise.elasticsearch.configuration;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration 
{
	@Bean
	public JestClient jestClient()
	{
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(System.getProperty("elasticsearch.url", "http://localhost:9200"))
		.multiThreaded(true).readTimeout(9000).build());

		return factory.getObject();
    }
	
}
