package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class NavigationEquipment {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String icon;
	
	
	
	public NavigationEquipment() {
		super();
	}



	public NavigationEquipment(String name, String icon) {
		super();
		this.name = name;
		this.icon = icon;
	}



	public NavigationEquipment(Long id, String name, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
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



	public String getIcon() {
		return icon;
	}



	public void setIcon(String icon) {
		this.icon = icon;
	}



	@Override
	public String toString() {
		return "NavigationEquipment [id=" + id + ", name=" + name + ", icon=" + icon + "]";
	}
	
	

}
