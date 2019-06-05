package com.hrd.springhomework.repository.ArticleRepository;

import com.hrd.springhomework.repository.model.Article;

import java.util.List;

public interface ArticleRepository {
    boolean add(Article article);
    boolean remove(Article article);
//    void update(Article article, Article articleUpdate);
//    Article find(Article article);
    List<Article> findAll();

    Article find(int id);

    List<Article> paginate(int page, int limit);

    void update(Article article);

    int getLastId();
}
