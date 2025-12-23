package org.example.javajdbctemplate.DI;

import org.springframework.stereotype.Component;

@Component("moteurDiesel")
public class MoteurDiesel implements  Engine{

    public MoteurDiesel(){
        System.out.println("instancie un moteur en diesl");
    }
    @Override
    public void rouler() {
        System.out.println("rouler en diesel");
    }
}
