package com.nelson.dao;

import java.util.ArrayList;
import java.util.List;

import com.nelson.beans.Utilisateur;
import java.sql.*;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private DaoFactory daoFactory;
	
	public UtilisateurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Utilisateur utilisateur) {
		
		Connection connexion = null;
		PreparedStatement prepareStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			prepareStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) Values (?,?);");
			
			prepareStatement.setString(1, utilisateur.getNom());
			prepareStatement.setString(2, utilisateur.getPrenom());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public List<Utilisateur> lister() {
		
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		Connection connexion = null;
		Statement  statement = null;
		ResultSet resultat = null;
		 
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT nom, prenom FROM noms");
		
			while(resultat.next()) {
			
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				
				Utilisateur utilisateur = new Utilisateur();
				
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				
				utilisateurs.add(utilisateur);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utilisateurs;
	}

}
