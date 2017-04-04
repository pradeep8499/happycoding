package com.target.enterprise.elasticsearch.service;

import java.util.Map;

import com.target.enterprise.elasticsearch.model.Customer;

public interface ElasticSearchService 
{
	String createCustomerIndex(String indexName) throws Exception;
	String loadDataIntoCustomerIndex(Customer customer) throws Exception;
	String searchContentWithinCustomerIndex(Map<String,String> jsonMap) throws Exception;
	void deleteIndex(String indexName) throws Exception;
}
