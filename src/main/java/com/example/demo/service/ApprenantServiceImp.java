package com.example.demo.service;

import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Formateur;
import com.example.demo.repository.ApprenantRepository;
import com.example.demo.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class ApprenantServiceImp implements ApprenantService {
    @ Autowired
    private ApprenantRepository apprenantRepository;
    @Override
    public List<Apprenant> getAllApprenant() {
        return apprenantRepository.findAll();
    }

    @Override
    public Apprenant findApprenantById(Long id) {
        Optional<Apprenant> formOptional = apprenantRepository.findById(id);
        if (formOptional.isEmpty()) {
            return null;
        } else {
            return formOptional.get();
        }
    }

    @Override
    public Apprenant createApprenant(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    @Override
    public Apprenant updateApprenant(Apprenant apprenant) {
        // Vérifier si l'id n'est pas null
        Long apprenantId = apprenant.getId();
        if (apprenantId == null) {
            // Gérer ou journaliser l'erreur et renvoyer une réponse appropriée
            throw new IllegalArgumentException("L'ID d'apprenant ne peut pas être null");
        }

        // Utiliser findById pour vérifier si l'apprenant existe
        Optional<Apprenant> existingApprenantOptional = apprenantRepository.findById(apprenantId);

        // Vérifier si l'apprenant' existe
        if (existingApprenantOptional.isEmpty()) {
            // Gérer ou journaliser l'erreur et renvoyer une réponse appropriée
            throw new EntityNotFoundException("Apprenant non trouvé avec l'ID : " + apprenantId);
        }

        // Mise à jour de l'entité Apprenant existante avec les nouvelles données
       Apprenant existingApprenant = existingApprenantOptional.get();
        existingApprenant.setNom(apprenant.getNom());
        existingApprenant.setPrenom(apprenant.getPrenom());
        // Autres champs à mettre à jour...

        // Enregistrer les modifications dans le repository
        return apprenantRepository.save(existingApprenant);
    }

    @Override
    public void deleteApprenant(long id) {
        apprenantRepository.deleteById(id);

    }
}
