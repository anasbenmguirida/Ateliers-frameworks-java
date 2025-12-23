package org.example.bootjdbc.services;

import lombok.NoArgsConstructor;
import org.example.bootjdbc.DAO.ICRUD;
import org.example.bootjdbc.mappers.ArticleMapper;
import org.example.bootjdbc.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService implements ICRUD<Article> {
    private final JdbcTemplate jdbcTemplate ;

    public ArticleService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate ;
    }


    @Override
    public List<Article> getAll() {
        String query = "select *from article" ;
        List<Article> listesDesArticles = jdbcTemplate.query(query , new ArticleMapper());
        System.out.println("******* logging *****************");
        for(Article article : listesDesArticles){
            System.out.println(article.getDesignation());
        }
        return  listesDesArticles ;
    }

    @Override
    public void create(Article article) {
        jdbcTemplate.update("insert into article values (? , ? , ?)" , article.getCode() , article.getDesignation() , article.getPrix());
    }

    @Override
    public void update(Article article) {
        jdbcTemplate.update("update article set designation  = ? where code =  ?" , article.getDesignation() , article.getCode() );
    }


}
