package com.daicent.connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {	
	Connection connection;
	public static void main(String[] args) throws Exception {
		int choice = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Connecions con = new Connecions();
		do {
			System.out.println("1. INSERT");
			System.out.println("2. DISPLAY ALL");
			System.out.println("3. SEARCH ID");
			System.out.println("4. SEARCH QUANTITY");
			System.out.println("5. ZXCZXCXC");
			System.out.println("0. EXIT");
			System.out.println("ENTER YOUR CHOICE: ");

			choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
			case 1:
				System.out.println("ENTER HOW MANY PRODUCT YOU WANT: ");
				Integer n = Integer.parseInt(br.readLine());
				for (int i=0; i<n;i++) {
					System.out.println("ENTER ID: ");
					int id = Integer.parseInt(br.readLine());
					System.out.println("ENTER NAME: ");
					String namePro = br.readLine();
					System.out.println("ENTER ID CATEDETAIL: ");
					int idCateDetail = Integer.parseInt(br.readLine());
					System.out.println("ENTER QUANTITY: ");
					int quantity = Integer.parseInt(br.readLine());
					con.insert(id, namePro, idCateDetail, quantity);
				}			
				break;
			case 2:
				con.display();
				break;
			case 3:
				System.out.println("ENTER ID TO SEARCH: ");
				int id = Integer.parseInt(br.readLine());
				con.serachById(id);
				break;
			case 4:
				System.out.println("ENTER QUANTITY TO SEARCH: ");
				int quantity = Integer.parseInt(br.readLine());
				con.serachByQuantity(quantity);
				break;
			case 5:
				con.mapCategory();
				break;
			case 0:
				con.close();
				System.out.println("----BYE----");
				break;
			default:
				break;
			}
		} while (choice!=0);
	}
	
}
