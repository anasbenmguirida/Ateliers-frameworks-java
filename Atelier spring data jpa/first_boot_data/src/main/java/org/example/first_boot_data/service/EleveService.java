package org.example.first_boot_data.service;



import org.example.first_boot_data.entity.Eleve;
import org.example.first_boot_data.repository.IEleveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EleveService {

    private final IEleveRepository repository ;

    public EleveService(IEleveRepository repository){
        this.repository = repository ;
    }

    public void createEleve(Eleve eleve){
        this.repository.save(eleve) ;
    }


    public List<Eleve> getAll(){
        return this.repository.findAll() ;
    }

    public Eleve getById(int id){
        Optional<Eleve> optionalEleve = repository.findById(id);
        if(optionalEleve!=null){
            return optionalEleve.get() ;
        }
        return null ;
    }

    public void delete(int id){
        this.repository.deleteById(id) ;
    }



}
