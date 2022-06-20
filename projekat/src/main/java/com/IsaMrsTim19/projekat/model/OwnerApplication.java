package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class OwnerApplication {

	@Id
	@GeneratedValue
	private Long id;
	
	@Relationship(type="FOR_OWNER", direction=Direction.OUTGOING)
	private Owner owner;
	
	private String requestDescription;

	public OwnerApplication() {
		super();
	}

	public OwnerApplication(Owner owner, String requestDescription) {
		super();
		this.owner = owner;
		this.requestDescription = requestDescription;
	}

	public OwnerApplication(Long id, Owner owner, String requestDescription) {
		super();
		this.id = id;
		this.owner = owner;
		this.requestDescription = requestDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	
	
	
}
