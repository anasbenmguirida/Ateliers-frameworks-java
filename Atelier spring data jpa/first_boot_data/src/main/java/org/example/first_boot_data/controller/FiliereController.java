package org.example.first_boot_data.controller;

import org.example.first_boot_data.entity.Cour;
import org.example.first_boot_data.entity.Filiere;
import org.example.first_boot_data.repository.ICourRepository;
import org.example.first_boot_data.repository.IIfiliereRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filieres")
public class FiliereController {

    private final IIfiliereRepository filiereService ;
    public FiliereController(IIfiliereRepository filiereService ){
        this.filiereService = filiereService  ;
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<Filiere> filieres = filiereService.findAll() ;
        model.addAttribute("filieres" , filieres);
        return "filiere/list" ;
    }

    @GetMapping("/ajouter")
    public String save(Model model){
        return "filiere/form" ;
    }

    @PostMapping("/save")
    public String saveCour(@ModelAttribute Filiere filiere , Model model){
        this.filiereService.save(filiere);
        return "redirect:/filieres/all" ;
    }


    @GetMapping("/delete/{id}")
    public String deleteFiliere(@PathVariable int id){
        this.filiereService.deleteById(id);
        return "redirect:/filieres/all" ;
    }
}
