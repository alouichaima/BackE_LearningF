package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Apprenant;
import com.example.demo.repository.ApprenantRepository;
import com.example.demo.service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Formateur;
import com.example.demo.repository.FormateurRepository;
import com.example.demo.service.FormateurService;

@RequestMapping("/apprenant")
@RestController
public class ApprenantController {

    @Autowired
    private ApprenantService apprenantService;

    @Autowired
    private ApprenantRepository apprenantRepository;

    @GetMapping(path="/all")
    public List<Apprenant> getAllApprenant() {
        return apprenantService.getAllApprenant();

    }

    @PostMapping(path="/add")
    public Apprenant createApprenant(@RequestBody Apprenant apprenant) {
        return apprenantService.createApprenant(apprenant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apprenant>  findApprenantById (@PathVariable Long id) {
       Apprenant apprenant = apprenantService.findApprenantById(id);
        if (apprenant==null) {
            return new ResponseEntity<Apprenant>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<Apprenant>(apprenant, HttpStatus.OK);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Apprenant> updateApprenant(@PathVariable Long id, @RequestBody Apprenant apprenant) {
        // Assurez-vous que l'ID est défini sur le apprenant
        apprenant.setId(id);

        // Appel de la méthode updateApprenant du service
       Apprenant updatedApprenant = apprenantService.updateApprenant(apprenant);

        // Retour de la réponse avec le apprenant mis à jour
        return ResponseEntity.ok(updatedApprenant);
    }

    @DeleteMapping(path= "/{id}")
    public void deleteCoach(@PathVariable Long id) {
        apprenantService.deleteApprenant(id);
    }

}
