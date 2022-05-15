package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class AdditionalService {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String price;
	
}
