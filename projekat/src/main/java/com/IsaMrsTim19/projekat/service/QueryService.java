package com.IsaMrsTim19.projekat.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

@Service
public class QueryService {

	public String generateSearchQuery(Map<String, String> queryParams) {
		System.out.println(queryParams);
		String query = "MATCH (n";
		String condition = " WHERE ";
		String ret = " RETURN n";
		boolean conditionExists = false;
		
		if(queryParams.containsKey("type")) {
			query += ":" + queryParams.get("type") + ") ";
		}else {
			query += ") ";
		}
		
		if(queryParams.containsKey("numOfPeople")) {
			if(conditionExists) {
				condition += " AND ";
			}
			condition += "n.numOfPeople >= " + queryParams.get("numOfPeople");
			conditionExists = true;
		}
		
		if(queryParams.containsKey("dateFrom")) {
			if(conditionExists) {
				condition += " AND ";
			}
			condition += "n.avaliableFrom <= date('" + queryParams.get("dateFrom") + "')";
			conditionExists = true;
		}
		
		if(queryParams.containsKey("dateTo")) {
			if(conditionExists) {
				condition += " AND ";
			}
			condition += "n.avaliableUntil >= date('"+ queryParams.get("dateTo") + "')";
			conditionExists = true;
		}
		
		
		if(conditionExists) {
			query += condition;
		}
		
		
		System.out.println(query + ret);
		return query + ret;
	}
	
	
}
