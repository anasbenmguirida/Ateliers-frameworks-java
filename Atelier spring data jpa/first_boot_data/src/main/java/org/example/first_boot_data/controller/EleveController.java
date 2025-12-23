package org.example.first_boot_data.controller;


import org.example.first_boot_data.entity.Cour;
import org.example.first_boot_data.entity.Eleve;
import org.example.first_boot_data.entity.Filiere;
import org.example.first_boot_data.repository.IIfiliereRepository;
import org.example.first_boot_data.service.EleveService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/eleve")
public class EleveController {

    private final EleveService service ;
    private final IIfiliereRepository filiereService  ;

    public EleveController(EleveService service , IIfiliereRepository filiereService){
        this.service = service ;
        this.filiereService = filiereService ;
    }


    @GetMapping("/all")
    public String getAll( Model model){
        System.out.println("************* je suis arriver dans le controlleur *********** ");
       List<Eleve> etudiants =  service.getAll() ;
       model.addAttribute("eleves" , etudiants) ;
       return "eleve/list" ;

    }

    @GetMapping("/ajouter")
    public String ajouterEleve(Model model){
        List<Filiere> filieres = filiereService.findAll() ;
        model.addAttribute("filieres" , filieres) ;
        return "eleve/form" ;
    }

    @PostMapping("/save")
    public String saveEleve(@ModelAttribute Eleve e , Model model){
        this.service.createEleve(e);
        System.out.println("*************** je suis la pour enregistrer l'eleve  ******************");
        System.out.println(e.getMatricule() + "      " + e.getNomComplet() + "        " );
        List<Eleve> etudiants =  service.getAll() ;
        model.addAttribute("eleves" , etudiants) ;
        return "redirect:/eleve/all" ;
    }

    @GetMapping("/delete/{id}")
    public String deleteEleve(@PathVariable int id){
        this.service.delete(id);
        return "redirect:/eleve/all" ;
    }

    @GetMapping("/edit/{id}")
    public String editCour(@PathVariable int id, Model model) {
        Eleve eleve = service.getById(id) ;
        List<Filiere> filieres = filiereService.findAll() ;
        model.addAttribute("eleve" , eleve);
        model.addAttribute("allFilieres" , filieres);
        return "eleve/edit";
    }


}
