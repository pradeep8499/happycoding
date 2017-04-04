package com.target.enterprise.elasticsearch.repository.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.target.enterprise.elasticsearch.model.Customer;
import com.target.enterprise.elasticsearch.repository.ElasticSearchRepository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

@Repository("elasticSearchRepository")
public class ElasticSearchRepositoryImpl implements ElasticSearchRepository 
{
	private static Logger log = Logger.getLogger(ElasticSearchRepositoryImpl.class);
	
	@Autowired
	private JestClient jestClient;
	
	@Override
	public String createIndex(String indexName) throws Exception 
	{
		//check if the index exists already
		IndicesExists indicesExists = new IndicesExists.Builder(indexName).build();
		boolean indexExists = jestClient.execute(indicesExists).isSucceeded();
		
		if( indexExists ) 
		{
			return "An index with that name already exists.";
		}
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("settings.json");
	    String settings = IOUtils.toString(in, "UTF-8");
		
        JestResult result = jestClient.execute(new CreateIndex.Builder(indexName).settings(settings).build());
        
		return result.getJsonString();
	}

	@Override
	public String loadDataIntoCustomerIndex(Customer customer) throws Exception
	{
		Index index = new Index.Builder(customer).index("customer").type("logs").setHeader("Content-Type", "application/json").build();
	    return jestClient.execute(index).getJsonString();
	}

	@Override
	public String searchContentWithinCustomerIndex(Map<String, String> jsonMap) throws IOException 
	{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("query.json");
	    String jsonTxt = IOUtils.toString(in, "UTF-8");
	      
	    String modifiedQuery = jsonTxt.replace("${query expression}", jsonMap.get("query")).replace("${size}", jsonMap.get("size"));
       
	    log.debug("modified query is " +modifiedQuery); 	   
	    
        Search.Builder searchBuilder = new Search.Builder(modifiedQuery).addIndex("customer");
      	SearchResult result = jestClient.execute(searchBuilder.build());
      	
      	return result.getSourceAsString();
	}

	@Override
	public void deleteIndex(String indexName) throws Exception
	{
		DeleteIndex indicesExists = new DeleteIndex.Builder(indexName).build();
        jestClient.execute(indicesExists);
	}
}
