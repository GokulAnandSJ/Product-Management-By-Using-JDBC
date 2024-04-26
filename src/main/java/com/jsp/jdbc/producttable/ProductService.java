package com.jsp.jdbc.producttable;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ProductService {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 for ADD ProductData");
		System.out.println("Enter 2 for Find the ProductBy its Id");
		System.out.println("Enter 3 for Find the Product By its Name ");
		System.out.println("Enter 4 for Find the Product Between Price");
		System.out.println("Enter 5 for Find All Product");
		System.out.println("Enter 6 for Update Product Price");
		System.out.println("Enter 7 for Delete the Product By its Id ");
		System.out.println("Enter 8 for Delete the Product By its Name");
		
		int val =sc.nextInt();
		
		if(val>8) {
			System.err.println("Invalid Value Please Enter the Value from 1 to 8 Based on your Work");
		}

		switch(val) {
		case 1:ProductService.add();
		break;

		case 2:ProductService.findProductById();
		break;

		case 3:ProductService.findProductByName();
		break;

		case 4:ProductService.findProductBetweenPrice();
		break;

		case 5:ProductService.findAllProduct();
		break;

		case 6:ProductService.updateProductPriceById();
		break;

		case 7:ProductService.deleteProductPriceById();
		break;

		case 8:ProductService.deleteProductByName();
		break;
		
		}
		sc.close();
	}
	
	
	public static void add() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Product Id");
		int productId = sc.nextInt();
		System.out.println("Enter the Product Name");
		String productName = sc.next();
		System.out.println("Enter the Product Brand");
		String productBrand = sc.next();
		System.out.println("Enter the Product Price");
		int productPrice = sc.nextInt();
		sc.close();

		try {
			String qury =("INSERT INTO productdb.product Values(?,?,?,?)");
			Connection con = DBSingleton.getConnectionObject();
			PreparedStatement pstmt = con.prepareStatement(qury);
			pstmt.setString(3, productBrand);  
			pstmt.setInt(1, productId);	   //This is the 1st-->? on the Query.
			pstmt.setString (2, productName);//This is the 2nd-->? on the Query.

			pstmt.setInt(4,productPrice);    //This is the 4th-->? on the Query.

			int rs =pstmt.executeUpdate(); 
			if(rs>0)
				System.out.println(rs+"DataEntered");
			else {
				System.out.println("No Data Entered");
			}

		} 
		catch (Exception e) {

			e.printStackTrace();
		}


	}

	public static void findProductById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Product Id");
		int productId = sc.nextInt();

		sc.close();

		try {
			String qury =("Select * from productdb.product where productId=? ");
			Connection con = DBSingleton.getConnectionObject();
			PreparedStatement pstmt = con.prepareStatement(qury);
			pstmt.setInt(1, productId);	   //This is the 1st-->? on the Query.

			ResultSet rs =pstmt.executeQuery(); 
			while (rs.next()) {
				int pid = rs.getInt("productId");
				String pName = rs.getString("productName");
				String pBrand = rs.getString("productBrand");
				int pPrice = rs.getInt("productPrice");

				System.out.println("ID is :"+pid+" and Name is :"+pName+" and Brand : "+pBrand+" Price is :"+pPrice);
			}


		} 
		catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	public static void findProductByName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Product Name");
		String productName = sc.next();

		sc.close();

		try {
			String qury =("Select * from productdb.product where productName= "+"'"+productName+"'");
			Connection con = DBSingleton.getConnectionObject();
			PreparedStatement pstmt = con.prepareStatement(qury);

			ResultSet rs =pstmt.executeQuery(); 
			while (rs.next()) {
				int pid = rs.getInt("productId");
				String pName = rs.getString("productName");
				String pBrand = rs.getString("productBrand");
				int pPrice = rs.getInt("productPrice");

				System.out.println("ID is :"+pid+" and Name is :"+pName+" and Brand : "+pBrand+" Price is :"+pPrice);
			}


		} 
		catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	public static void findProductBetweenPrice() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Product Price BetweenRange 1st and 2nd");
		int productPrice1 = sc.nextInt();
		int productPrice2 = sc.nextInt();

		sc.close();

		try {
			String qury =("Select * from productdb.product where productPrice Between ? and ?");
			Connection con = DBSingleton.getConnectionObject();
			PreparedStatement pstmt = con.prepareStatement(qury);
			pstmt.setInt(1, productPrice1);	   //This is the 1st-->? on the Query.
			pstmt.setInt(2, productPrice2);


			ResultSet rs =pstmt.executeQuery(); 
			while (rs.next()) {
				int pid = rs.getInt("productId");
				String pName = rs.getString("productName");
				String pBrand = rs.getString("productBrand");
				int pPrice = rs.getInt("productPrice");

				System.out.println("ID is :"+pid+" and Name is :"+pName+" and Brand :"+pBrand+" Price is :"+pPrice);
			}


		} 
		catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	public static void findAllProduct() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ALL Product List");

		sc.close();

		try {
			String qury =("Select * from productdb.product");
			Connection con = DBSingleton.getConnectionObject();
			Statement stmt = con.createStatement();

			ResultSet rs =stmt.executeQuery(qury); 
			while (rs.next()) {
				int pid = rs.getInt("productId");
				String pName = rs.getString("productName");
				String pBrand = rs.getString("productBrand");
				int pPrice = rs.getInt("productPrice");

				System.out.println("ID is :"+pid+" and Name is :"+pName+" and Brand :"+pBrand+" Price is :"+pPrice);
			}


		} 
		catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	public static void updateProductPriceById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Product Id");
		int productId = sc.nextInt();
		System.out.println("Enter New Product Price");
		int productPrice = sc.nextInt();

		sc.close();

		try {
			String qury =("UPDATE productdb.product SET productPrice=? where productId = "+productId);
			Connection con = DBSingleton.getConnectionObject();
			PreparedStatement pstmt = con.prepareStatement(qury);
			pstmt.setInt(1, productPrice);	 

			int rs =pstmt.executeUpdate(); 
			if(rs>=1) {
				System.out.println(rs+" Data Updated");
			}
			else {
				System.out.println("No Data Updated");
			}

		} 
		catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	public static void deleteProductPriceById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Product Id");
		int productId = sc.nextInt();


		sc.close();

		try {
			String qury =("DELETE From productdb.product where productId ="+productId);
			Connection con = DBSingleton.getConnectionObject();
			PreparedStatement pstmt = con.prepareStatement(qury);

			int rs =pstmt.executeUpdate(); 
			if(rs>=1) {
				System.out.println(rs+" Data Deleted");
			}
			else {
				System.out.println("No Data Deleted");
			}

		} 
		catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public static void deleteProductByName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Product Name");
		String productName = sc.next();

		sc.close();

		try {
			String qury =("DELETE From productdb.product where productName ="+"'"+productName+"'");
			Connection con = DBSingleton.getConnectionObject();
			PreparedStatement pstmt = con.prepareStatement(qury);

			int rs =pstmt.executeUpdate(); 
			
			if(rs>=1) {
				System.out.println(rs+" Data Deleted");
			}
			else {
				System.out.println("No Data Deleted");
			}

		} 
		catch (Exception e) {

			e.printStackTrace();
		}

	}

}


final class DBSingleton{
	private static Connection con;
	static {
		try {
			Properties prop = new Properties();


			FileReader read = new FileReader("Config/Product.properties");
			prop.load(read);
			String username = prop.getProperty("UserName");
			String pwd = prop.getProperty("Password");
			String url = prop.getProperty("url");
			String driver = prop.getProperty("Driver");

			Class.forName(driver);
			con = DriverManager.getConnection(url,username,pwd);  
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private DBSingleton() {

	}  
	public static Connection getConnectionObject() {
		return con;
	}
}
