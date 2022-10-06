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

public class Connecions {
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
	
	public ArrayList<CategoryDetail> selectByIdCategory(int idCate) {
		List<CategoryDetail> lisCategoryDetails = new ArrayList<CategoryDetail>();
		try {
			String mysql = "SELECT * FROM shop.categorydetail where idCate =?;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setInt(1, idCate);
			ResultSet resultSet = preStatemnt.executeQuery();
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("name");
				int idCategory = resultSet.getInt("idCate");
				CategoryDetail categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
				lisCategoryDetails.add(categoryDetail);
				System.out.printf("%-15s%-20s%5s\n",idCategoryDetail, nameCategoryDetail, idCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<CategoryDetail>) lisCategoryDetails;
	}

	
	 public List<Products>findByCategoryDetail(int idCateDetail){
	        List<Products> products = new ArrayList<>();
	        try (
	            PreparedStatement preparedStatement = connection.prepareStatement(
	            		"select id, namePro, quantity from shop.products where idCateDetail =?");) {
	            preparedStatement.setInt(1, idCateDetail);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("namePro");
	                int quantity= rs.getInt("quantity");
	                products.add(new Products(id, name,idCateDetail,quantity));
	                System.out.printf("%-15s%-20s%-20s%5s\n",id, name,idCateDetail,quantity);

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();;
	        }
	        return products;
	    }
	 public List<Products> findAllByCategory(int idCate) {
	       List<Products>products=new ArrayList<>();
	        try (
	             PreparedStatement preparedStatement = connection.prepareStatement(
	            		" select p.namePro, p.quantity, p.idCateDetail from shop.products p, shop.categorydetail cd, shop.category c \r\n"
	             		+ "where p.idCateDetail = cd.idCategoryDetail and cd.idCate = c.idCate and c.idCate = ?;");) {
	            preparedStatement.setInt(1, idCate);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String name = rs.getString("namePro");
	                int quantity = rs.getInt("quantity");
	                int idCateDetail =rs.getInt("idCateDetail");
	                products.add(new Products(name,idCateDetail,quantity));
	                System.out.printf("%-15s%-20s%-20s%5s\n", name,quantity,idCateDetail,idCate);
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return products;
	    }
}
