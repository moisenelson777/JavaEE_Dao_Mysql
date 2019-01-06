 package com.nelson.dao;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DaoFactory {

	private String url, user, password;
	
	public DaoFactory(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public static DaoFactory getInstance() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}
		DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/javaee","root","1234");
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		 Connection connexion = DriverManager.getConnection(url, user, password);
	        connexion.setAutoCommit(false);
	        return connexion; 
	    }
	
	//Recuperer le DAO
	
	public UtilisateurDao getUtilisateurDAO() {
		return new UtilisateurDaoImpl(this);
	}
}
