package com.target.enterprise.elasticsearch.repository;

import java.util.Map;

import com.target.enterprise.elasticsearch.model.Customer;

public interface ElasticSearchRepository 
{
	String createIndex(String indexName) throws Exception;
	String loadDataIntoCustomerIndex(Customer customer) throws Exception;
	String searchContentWithinCustomerIndex(Map<String,String> jsonMap) throws Exception;
	void deleteIndex(String indexName) throws Exception;
}
