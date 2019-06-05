package com.hrd.springhomework.repository;

import com.hrd.springhomework.repository.ArticleRepository.ArticleRepository;
import com.hrd.springhomework.repository.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepositoryImp implements ArticleRepository {
    private List<Article> articleList = new ArrayList<>();

    @Override
    public boolean add(Article article) {
        return articleList.add(article);
    }

    @Override
    public int getLastId() {
        int size = articleList.size();
        return (size != 0) ? articleList.get(articleList.size() - 1).getId() : 0;
    }

    private int getIndex(Article article) {
        return articleList.indexOf(articleList.stream().filter(x -> article.getId() == x.getId()).findFirst().orElse(null));
    }

    @Override
    public void update(Article article) {
        articleList.set(getIndex(article), article);
    }

    @Override
    public Article find(int id) {
        return articleList.stream().filter(x -> (id == x.getId())).findFirst().orElse(null);
    }

    @Override
    public List<Article> findAll() {
        return articleList;
    }

    @Override
    public boolean remove(Article article) {
        return articleList.remove(article);
    }
//
//    @Override
//    public void update(Article article, Article articleUpdate) {
//        articleList.set(articleList.indexOf(article), articleUpdate);
//    }
//
//    @Override
//    public Article find(Article article) {
//        return article;
//    }

}
