package com.hrd.springhomework.service.ArticleService;


import com.hrd.springhomework.repository.model.Article;

import java.util.List;

public interface ArticleService {
    void add(Article article);
//    void remove(int id);
//    void update(int id, Article article);
//    Article find(int id);
    List<Article> findAll();
}
