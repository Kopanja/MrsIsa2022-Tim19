package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class DeletionRequest {

	@Id
	@GeneratedValue
	private Long id;
	
	private String requestText;
	
	@Relationship(type="FROM_USER", direction=Direction.OUTGOING)
	private User user;

	public DeletionRequest() {
		super();
	}

	public DeletionRequest(String requestText, User user) {
		super();
		this.requestText = requestText;
		this.user = user;
	}

	public DeletionRequest(Long id, String requestText, User user) {
		super();
		this.id = id;
		this.requestText = requestText;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "DeletionRequest [id=" + id + ", requestText=" + requestText + ", user=" + user + "]";
	}


	
	
	
	
}
