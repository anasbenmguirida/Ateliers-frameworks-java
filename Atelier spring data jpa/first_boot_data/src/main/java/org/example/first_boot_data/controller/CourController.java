package org.example.first_boot_data.controller;

import org.example.first_boot_data.entity.Cour;
import org.example.first_boot_data.entity.Eleve;
import org.example.first_boot_data.entity.Filiere;
import org.example.first_boot_data.repository.ICourRepository;
import org.example.first_boot_data.repository.IIfiliereRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cours")
public class CourController {
    private final ICourRepository courService ;
    private final IIfiliereRepository filiereService  ;

    public CourController(ICourRepository courService ,IIfiliereRepository filiereService ){
        this.courService = courService;
        this.filiereService = filiereService  ;
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<Cour> cours = courService.findAll() ;
        model.addAttribute("cours" , cours);
        return "cours/list" ;
    }

    @GetMapping("/ajouter")
    public String save(Model model){
        List<Filiere> filieres = filiereService.findAll() ;
        model.addAttribute("filieres" , filieres) ;
        return "cours/form" ;
    }

    @PostMapping("/save")
    public String saveCour(@ModelAttribute @Validated Cour cour){
        this.courService.save(cour);
        return "redirect:/cours/all" ;
    }


    @GetMapping("/delete/{id}")
    public String deleteCour(@PathVariable int id){
        this.courService.deleteById(id);
        return "redirect:/cours/all" ;
    }

    @GetMapping("/edit/{id}")
    public String editCour(@PathVariable int id, Model model) {
        Cour cour = courService.findById(id).get();
        List<Filiere> allFilieres = filiereService.findAll();

        model.addAttribute("cour", cour);
        model.addAttribute("allFilieres", allFilieres);
        return "cours/edit";
    }



}
