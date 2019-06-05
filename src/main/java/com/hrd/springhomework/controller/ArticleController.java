package com.hrd.springhomework.controller;

import com.hrd.springhomework.repository.model.Article;
import com.hrd.springhomework.service.ArticleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
        for (int i = 0; i < 5; i++)
            articleService.add(new Article(i,"Spring","Sokha", "Cambodia", null));
    }
    @GetMapping({"", "/article"})
    public String index(Model model){
        System.out.println(articleService.findAll().size());
        model.addAttribute("articles", articleService.findAll());
        return "/articles/index";
    }

    @GetMapping("/article/create")
    public String create(Model model){
        Article article = new Article();
        article.setId(articleService.getLastId() + 1);
        model.addAttribute("article", article);
        return "/articles/create";
    }

    @DeleteMapping("/article/{id}")
    public String delete(@PathVariable int id){
        System.out.println(id);
        articleService.remove(id);
        return "redirect:/article";
    }

    @GetMapping("/article/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("article", articleService.find(id));
        return "/articles/edit";
    }

    @GetMapping("/article/show/{id}")
    public String show(@PathVariable int id, Model model){
        Article article = articleService.find(id);
        System.out.println("ID = " + id);
        System.out.println(article.toString());
        model.addAttribute("article", article);
        return "/articles/show";
    }

    @PostMapping("/article/update")
    public String update(@Valid @ModelAttribute Article article, BindingResult bindingResult, MultipartFile file){
        if(bindingResult.hasErrors()){
            return "/article/update/" + article.getId();
        }
        articleService.update(article);
        return "redirect:/";
    }

    @PostMapping("/article/add")
    public String add(@Valid @ModelAttribute Article article, BindingResult bindingResult, MultipartFile file){

        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "/articles/create";
        }

        String imageName = UUID.randomUUID().toString();
        String extension;
        if(file != null && file.getOriginalFilename() != null) {
            if(!file.getOriginalFilename().isEmpty()) {
                extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
                imageName += extension;
                try {
                    Files.copy(file.getInputStream(), Paths.get("src/main/resources/static/image/" + imageName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                article.setImage(imageName);
//                return;
            }
        }

//        return "redirect:/fileForm";
        System.out.println(article.toString());
        articleService.add(article);
        return "redirect:/article/create";
    }
}
