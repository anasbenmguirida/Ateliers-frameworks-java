package org.example.bootjdbc.mappers;

import org.example.bootjdbc.model.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Article article = new Article();
        article.setCode(resultSet.getInt("code"));
        article.setDesignation(resultSet.getString("designation"));
        article.setPrix(resultSet.getDouble("prix"));
        return article;
    }
}
