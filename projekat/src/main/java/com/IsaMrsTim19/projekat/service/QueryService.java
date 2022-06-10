package com.IsaMrsTim19.projekat.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

@Service
public class QueryService {

	public String generateSearchQuery(Map<String, String> queryParams) {
		String query = "MATCH (n";
		String condition = " WHERE ";
		String ret = " RETURN n";
		
		for(Entry<String, String> p : queryParams.entrySet()) {
			
			if(p.getKey().equals("type")) {
				System.out.println(p.getValue().getClass());
				query += ":" + p.getValue() + ") ";
			}
			else if(p.getKey().equals("numOfPeople")) {
				condition += "n.numberOfPeople >= " + p.getValue();
			}
		}
		
		return query + condition + ret;
	}
	
	
}
