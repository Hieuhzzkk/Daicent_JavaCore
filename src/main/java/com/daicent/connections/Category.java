package com.daicent.connections;

public class Category {
	private int idCate;
	private String name;
	
	
	public Category(int idCate, String name) {
		super();
		this.idCate = idCate;
		this.name = name;
	}
	public int getIdCate() {
		return idCate;
	}
	public void setIdCate(int idCate) {
		this.idCate = idCate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCate;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (idCate != other.idCate)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Category\n" + "idCate= " + idCate + " " + "name= " + name;
	}
	
}
