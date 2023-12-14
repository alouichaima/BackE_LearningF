package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Formateur;
import com.example.demo.repository.FormateurRepository;
@Service
public class FormateurServiceImp implements FormateurService {
	
	@Autowired
	private FormateurRepository formateurRepository;


	@Override
	public List<Formateur> getAllFormateur() {
		return formateurRepository.findAll();

	}

	@Override
	public Formateur findFormateurById(Long id) {
		Optional<Formateur> formOptional = formateurRepository.findById(id);
		if (formOptional.isEmpty()) {
			return null;
		} else {
			return formOptional.get();
		}
	}

	@Override
	public Formateur createFormateur(Formateur formateur) {
		return formateurRepository.save(formateur);

	}

	@Override
	 public Formateur updateFormateur(Formateur formateur) {
        // Vérifier si l'id n'est pas null
        Long formateurId = formateur.getId();
        if (formateurId == null) {
            // Gérer ou journaliser l'erreur et renvoyer une réponse appropriée
            throw new IllegalArgumentException("L'ID du formateur ne peut pas être null");
        }

        // Utiliser findById pour vérifier si le formateur existe
        Optional<Formateur> existingFormateurOptional = formateurRepository.findById(formateurId);
        
        // Vérifier si le formateur existe
        if (existingFormateurOptional.isEmpty()) {
            // Gérer ou journaliser l'erreur et renvoyer une réponse appropriée
            throw new EntityNotFoundException("Formateur non trouvé avec l'ID : " + formateurId);
        }

        // Mise à jour de l'entité Formateur existante avec les nouvelles données
        Formateur existingFormateur = existingFormateurOptional.get();
        existingFormateur.setNom(formateur.getNom());
        existingFormateur.setPrenom(formateur.getPrenom());
        // Autres champs à mettre à jour...

        // Enregistrer les modifications dans le repository
        return formateurRepository.save(existingFormateur);
    }
	@Override
	public void deleteFormateur(long id) {
		formateurRepository.deleteById(id);
	}



}
