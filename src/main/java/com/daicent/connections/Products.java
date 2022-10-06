package com.daicent.connections;

public class Products {
	private int id;
	private String namePro;
	private int idCateDetail;
	private int quantity;
	public Products(int id, String namePro, int idCateDetail, int quantity) {
		super();
		this.id = id;
		this.namePro = namePro;
		this.idCateDetail = idCateDetail;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNamePro() {
		return namePro;
	}
	public void setNamePro(String namePro) {
		this.namePro = namePro;
	}
	public int getIdCateDetail() {
		return idCateDetail;
	}
	public void setIdCateDetail(int idCateDetail) {
		this.idCateDetail = idCateDetail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idCateDetail;
		result = prime * result + ((namePro == null) ? 0 : namePro.hashCode());
		result = prime * result + quantity;
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
		Products other = (Products) obj;
		if (id != other.id)
			return false;
		if (idCateDetail != other.idCateDetail)
			return false;
		if (namePro == null) {
			if (other.namePro != null)
				return false;
		} else if (!namePro.equals(other.namePro))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Products" + "id=" + id + "namePro= " + namePro + "idCateDetail= " + idCateDetail
				+ "quantity= "+ quantity;
	}
	
}
