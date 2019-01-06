package com.nelson.dao;

import java.util.List;

import com.nelson.beans.Utilisateur;

public interface UtilisateurDao {

	void ajouter(Utilisateur utilisateur);
	List<Utilisateur> lister();
	
}
