package com.example.demo.service;

import java.util.List;


import com.example.demo.entity.Formateur;


public interface FormateurService  {
	public List<Formateur> getAllFormateur();
	public Formateur findFormateurById (Long id);
	public Formateur createFormateur( Formateur formateur);
	public Formateur updateFormateur( Formateur formateur);
	public void deleteFormateur( long id);


}
