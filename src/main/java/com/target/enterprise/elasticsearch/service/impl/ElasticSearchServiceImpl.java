package com.target.enterprise.elasticsearch.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.enterprise.elasticsearch.model.Customer;
import com.target.enterprise.elasticsearch.repository.ElasticSearchRepository;
import com.target.enterprise.elasticsearch.service.ElasticSearchService;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService 
{
	@Autowired
	private ElasticSearchRepository elasticSearchRepository;

	@Override
	public String createCustomerIndex(String indexName) throws Exception 
	{
		return elasticSearchRepository.createIndex(indexName);
	}

	@Override
	public String loadDataIntoCustomerIndex(Customer customer) throws Exception 
	{
		return elasticSearchRepository.loadDataIntoCustomerIndex(customer);
	}

	@Override
	public String searchContentWithinCustomerIndex(Map<String, String> jsonMap) throws Exception 
	{
		return elasticSearchRepository.searchContentWithinCustomerIndex(jsonMap);
	}

	@Override
	public void deleteIndex(String indexName) throws Exception 
	{
		elasticSearchRepository.deleteIndex(indexName);
	}
}
