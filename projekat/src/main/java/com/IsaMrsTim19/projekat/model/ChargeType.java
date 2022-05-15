package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class ChargeType {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	public ChargeType(String name) throws Exception {
		if(name.equals("PER_DAY") || name.equals("PER_HOUR") || name.equals("ONE_CHARGE") || name.equals("FREE")) {
			this.name = name;
		}else {
			throw new Exception("Charge Type doesn't exist");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
	
	

}
