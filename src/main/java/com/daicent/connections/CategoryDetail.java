package com.daicent.connections;

public class CategoryDetail {
	private int idCategoryDetail;
	private String name;
	private int idCate;
	public CategoryDetail(int idCategoryDetail, String name, int idCate) {
		super();
		this.idCategoryDetail = idCategoryDetail;
		this.name = name;
		this.idCate = idCate;
	}
	public int getIdCategoryDetail() {
		return idCategoryDetail;
	}
	public void setIdCategoryDetail(int idCategoryDetail) {
		this.idCategoryDetail = idCategoryDetail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdCate() {
		return idCate;
	}
	public void setIdCate(int idCate) {
		this.idCate = idCate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCate;
		result = prime * result + idCategoryDetail;
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
		CategoryDetail other = (CategoryDetail) obj;
		if (idCate != other.idCate)
			return false;
		if (idCategoryDetail != other.idCategoryDetail)
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
		return "CategoryDetail" + "idCategoryDetail= " + idCategoryDetail + "name= " + name
				+ "idCate= " + idCate;
	}
	
}
