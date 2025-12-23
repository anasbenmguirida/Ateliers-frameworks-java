package org.example.javajdbctemplate.hibernate.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class DossierStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;

    private Date dateCreation ;

    @OneToOne(mappedBy = "dossier")
    private Student student ;
}
