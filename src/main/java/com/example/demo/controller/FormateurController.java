package com.example.demo.controller;

import java.util.List;

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

@RequestMapping("/formateur")
@RestController
public class FormateurController {
	
	@Autowired
	private FormateurService formateurService;
	
	@Autowired
	private FormateurRepository formateurRepository;
	
	@GetMapping(path="/all")
	public List<Formateur> getAllFormateur() {
		return formateurService.getAllFormateur();
		
	}
	
	@PostMapping(path="/add")
	public Formateur createFormateur(@RequestBody Formateur formateur) {
		return formateurService.createFormateur(formateur);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Formateur>  findFormateurById (@PathVariable Long id) {
		Formateur formateur = formateurService.findFormateurById(id);
		if (formateur==null) {
			return new ResponseEntity<Formateur>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Formateur>(formateur, HttpStatus.OK);
		}
	}
    @PutMapping("/{id}")
    public ResponseEntity<Formateur> updateFormateur(@PathVariable Long id, @RequestBody Formateur formateur) {
        // Assurez-vous que l'ID est défini sur le formateur
        formateur.setId(id);

        // Appel de la méthode updateFormateur du service
        Formateur updatedFormateur = formateurService.updateFormateur(formateur);

        // Retour de la réponse avec le formateur mis à jour
        return ResponseEntity.ok(updatedFormateur);
    }
    
	@DeleteMapping(path= "/{id}") 
	public void deleteCoach(@PathVariable Long id) {
		formateurService.deleteFormateur(id);
	}

}
