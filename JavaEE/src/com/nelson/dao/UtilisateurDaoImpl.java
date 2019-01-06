package com.nelson.dao;

import java.util.ArrayList;
import java.util.List;

import com.nelson.beans.BeanException;
import com.nelson.beans.Utilisateur;
import java.sql.*;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private DaoFactory daoFactory;
	
	public UtilisateurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Utilisateur utilisateur) throws DaoException{
		
		Connection connexion = null;
		PreparedStatement prepareStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			prepareStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) Values (?,?);");
			
			prepareStatement.setString(1, utilisateur.getNom());
			prepareStatement.setString(2, utilisateur.getPrenom());
			
			prepareStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }

    }



	@Override
	public List<Utilisateur> lister() throws DaoException {
		
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
            throw new DaoException("Impossible de communiquer avec la base de données");
        } catch (BeanException e) {
            throw new DaoException("Les données de la base sont invalides");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return utilisateurs;
    }

}