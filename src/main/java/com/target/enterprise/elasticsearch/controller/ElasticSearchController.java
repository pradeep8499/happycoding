package com.target.enterprise.elasticsearch.controller;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.enterprise.elasticsearch.model.Customer;
import com.target.enterprise.elasticsearch.service.ElasticSearchService;

@RestController
@RequestMapping(value="/ElasticSearchIndices")
public class ElasticSearchController 
{
	private static Logger log = Logger.getLogger(ElasticSearchController.class);
	
	@Autowired
	private ElasticSearchService elasticSearchService;
	
	@RequestMapping(value="/create/{indexName}", produces="application/json", method = POST)
	public String createCustomerIndex(@PathVariable String indexName) throws Exception
	{
		
		String response = "";
		try
		{
			response = elasticSearchService.createCustomerIndex(indexName);		
		}
		catch(Exception e)
		{
			log.error("unable to create customer index" +e);
			response = "unable to create customer index";
		}
	
		return response;
	}

	@RequestMapping(value="/customer/loadData", produces="application/json", method = POST)
	public String loadDataIntoIndex( @RequestBody String requestData ) 
	{
		String response = "";
		try
		{
			Customer customer = new ObjectMapper().readValue(requestData, Customer.class);
			response = elasticSearchService.loadDataIntoCustomerIndex(customer);
		}
		catch(Exception e)
		{
			log.error("unable to save data into customer index", e);
			response = "unable to save data into customer index. Please check your data";
		}
      
		return response;
	}
	
	@RequestMapping(value="/customer/searchContent", method = POST)
	public String searchContentWithinIndex( @RequestBody String searchQueryJson) throws Exception
	{
		String response = "";
		try
		{
			@SuppressWarnings("unchecked")
			Map<String,String> jsonMap = new ObjectMapper().readValue(searchQueryJson, Map.class);
		    response = elasticSearchService.searchContentWithinCustomerIndex(jsonMap);
		}
		catch(Exception e)
		{
			log.error("unable to search data in customer index", e);
			response = "unable to search data in customer index. Please try again.";
		}
		
      	return response;
	}
	
	@RequestMapping(value="/delete/{indexName}", method = DELETE)
	public void deleteCustomerIndex(@PathVariable String indexName ) throws Exception
	{
		elasticSearchService.deleteIndex(indexName);
	}
}
