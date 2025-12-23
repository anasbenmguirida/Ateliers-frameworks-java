package org.example.javajdbctemplate.DI;

import org.example.javajdbctemplate.Transactions.entities.Client;
import org.example.javajdbctemplate.Transactions.services.ClientService;
import org.example.javajdbctemplate.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Voiture {

    private final Engine moteur ;
    @Autowired
    public Voiture(@Qualifier("moteurEscence") Engine moteur){
        this.moteur = moteur;
    }
    void rouler(){
        this.moteur.rouler();
    }


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ClientService service = context.getBean( ClientService.class) ;

        service.transferMoney(1 , 2 , 1000.0f);

    }
}
