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
		String ret = " RETURN n,r,c";
		boolean conditionExists = false;
		
		if(queryParams.containsKey("type")) {
			query += ":" + queryParams.get("type") + ")-[r:IS_IN]->(c:City) ";
		}else {
			query += ")-[r:IS_IN]->(c:City) ";
		}
		
		if(queryParams.containsKey("numOfPeople")) {
			if(conditionExists) {
				condition += " AND ";
			}
			condition += "n.numOfPeople >= " + queryParams.get("numOfPeople");
			conditionExists = true;
		}
		
		if(queryParams.containsKey("city")) {
			if(conditionExists) {
				condition += " AND ";
			}
			condition += "c.name = '" + queryParams.get("city") + "'";
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
