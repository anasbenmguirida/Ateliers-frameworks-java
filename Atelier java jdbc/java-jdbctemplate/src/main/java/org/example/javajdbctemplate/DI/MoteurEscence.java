package org.example.javajdbctemplate.DI;

import org.springframework.stereotype.Component;

@Component("moteurEscence")
public class MoteurEscence implements Engine{

    public MoteurEscence(){
        System.out.println("instanciating moteur essence");
    }

    @Override
    public void rouler() {
        System.out.println("rouler avec de lescence ");
    }
}
