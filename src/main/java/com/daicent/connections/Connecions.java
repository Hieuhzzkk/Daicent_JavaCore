package com.daicent.connections;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Connecions implements Comparable<Category>{
	PreparedStatement ps;
	Connection connection;
	Statement stmt;
	ResultSet rs;
	Connecions() {
		try {
			String url ="jdbc:mySQL://localhost:3308/shop";
			String userName="root";
			String password ="123456";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection success");
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String ConnectrionUrl = "jdbc:sqlserver://localhost;database=School";
//			String username = "sa";
//			String password = "123";
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	public void insert(int id, String namePro, int idCateDetail, int quantity) throws Exception{
		
		ps = connection.prepareStatement("INSERT INTO `shop`.`products` (`id`, `namePro`, `idCateDetail`, `quantity`) VALUES (?, ?, ?, ?);\r\n"
				+ "");
		ps.setInt(1, id);
		ps.setString(2, namePro);
		ps.setInt(3, idCateDetail);
		ps.setInt(4, quantity);
		ps.executeUpdate();

	}
	public void display() throws SQLException {
		ps = connection.prepareStatement("SELECT * FROM shop.products");
		rs = ps.executeQuery();
		while (rs.next()) {
			System.out.printf("%-15s%-20s%-20s%5s\n", 
					rs.getString(1),rs.getString(2),rs.getInt(4),rs.getString(3));
			
		}
	}
	public void serachById(int id) throws SQLException {
		ps = connection.prepareStatement("SELECT * FROM shop.products WHERE id = ?");
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if (rs.next()) {
			System.out.printf("%-15s%-20s%-20s%5s\n", 
					rs.getString(1),rs.getString(2),rs.getInt(4),rs.getString(3));

		} else {
			System.out.println("NOT FOUND....!");
		}
		
	}
	public void serachByQuantity(int quantity) throws SQLException {
		boolean found = false;
		ps = connection.prepareStatement("SELECT * FROM shop.products WHERE quantity > ?");
		ps.setInt(1, quantity);
		rs = ps.executeQuery();
		while (rs.next()) {
			System.out.printf("%-15s%-20s%-20s%5s\n", 
						rs.getString(1),rs.getString(2),rs.getInt(4),rs.getString(3));
			found = true;
		} 
		
		if (!found) {
			System.out.println("NOT FOUND....!");
		} 
		
	}
	public void close() throws Exception{
		connection.close();
		System.out.println("Connection close");
	}
	
	public ArrayList<CategoryDetail> selectByIdCategory(Category t) {
		List<CategoryDetail> lisCategoryDetails = new ArrayList<CategoryDetail>();
		try {
			String url ="jdbc:mySQL://localhost:3308/shop";
			String userName="root";
			String password ="123456";
			connection = DriverManager.getConnection(url, userName, password);
			String mysql = "SELECT * FROM shop.categorydetail where idCate =?;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setInt(1, t.getIdCate());
			ResultSet resultSet = preStatemnt.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("name");
				int idCategory = resultSet.getInt("idCate");
				CategoryDetail categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
				lisCategoryDetails.add(categoryDetail);
				count++;
			}
			if (count > 0) {
				System.out.println("There are " + count + " CategoryDetail!");
			} else {
				System.out.println("No CategoryDetail!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<CategoryDetail>) lisCategoryDetails;
	}
	public TreeMap<Category, ArrayList<CategoryDetail>> mapCategory(){
		Map <Category, ArrayList<CategoryDetail>> map = new TreeMap<Category, ArrayList<CategoryDetail>> ();
		try {
			String url ="jdbc:mySQL://localhost:3308/shop";
			String userName="root";
			String password ="123456";
			connection = DriverManager.getConnection(url, userName, password);
			String mysql = "SELECT * FROM shop.category;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			ResultSet resultSet = preStatemnt.executeQuery();
			while (resultSet.next()) {
				int idCategory = resultSet.getInt("idCate");
				String nameCategory = resultSet.getString("name");
				Category category = new Category(idCategory, nameCategory);
				ArrayList<CategoryDetail> listCategoryDetails =selectByIdCategory(category);
				
				map.put(category,  listCategoryDetails);
			}
			Set<Category> setCategory = map.keySet();
	        for (Category key : setCategory) {
	        	System.out.println(key.toString());
	        	ArrayList<CategoryDetail> list = map.get(key);
	           for(CategoryDetail cd:list) {
	        	   System.out.println(cd.toString());
	           }
	        }			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (TreeMap<Category, ArrayList<CategoryDetail>>) map;
	}
	public int compareTo(Category o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
