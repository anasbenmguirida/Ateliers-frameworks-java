package org.example.bootjdbc.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.bootjdbc.model.Article;
import org.example.bootjdbc.services.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class ArticleController {
    private final ArticleService service ;

    public ArticleController(ArticleService service){
        this.service = service ;
    }

    @GetMapping("/")
    public String greetUser(Model model){
        model.addAttribute("message" , "hello user from the controller") ;
        return "index" ;
    }

    @GetMapping("/list")
    public String getArticles(Model model){
        System.out.println("************ dans le controlleur pour lister les articles *******");
       List<Article> articles = service.getAll() ;
       for(Article a : articles){
           System.out.println(a.getDesignation());
       }
       model.addAttribute("articles" , articles) ;
       return "list" ;
    }


    // pour l'ajout d'un article
    @GetMapping("/ajouter")
    public  String create(){
        return "articleForm";
    }
    @PostMapping("/ajouter")
    public String createArticle(@ModelAttribute Article article,
                                Model model) {
        try {
            service.create(article);
            return "redirect:/list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("article", article);
            return "articleForm";
        } catch (Exception e) {
            model.addAttribute("error", "Une erreur s'est produite: " + e.getMessage());
            model.addAttribute("article", article);
            return "articleForm";
        }
    }


    // pour l'edit d'un article
    @PostMapping("modifier/{code}")
    public String editArticle(Model model , Article article, @PathVariable int code){
        service.update(article);
        return "editArticle" ;
    }




}
