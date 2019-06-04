package com.hrd.springhomework.controller;

import com.hrd.springhomework.repository.model.Article;
import com.hrd.springhomework.service.ArticleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
//        for (int i = 0; i < 5; i++)
//            articleService.add(new Article("Spring","Sokha", "Cambodia", "fdjks"));
    }
    @GetMapping({"", "/article"})
    public String index(Model model){
        System.out.println(articleService.findAll().size());
        model.addAttribute("articles", articleService.findAll());
        return "/articles/index";
    }

    @GetMapping("/article/create")
    public String create(Model model){
        model.addAttribute("article", new Article());
        return "/articles/create";
    }

    @DeleteMapping("/article/{id}")
    public String delete(@PathVariable int id){
        System.out.println(id);
        articleService.remove(id);
        return "redirect:/article";
    }

    @PostMapping("/article/add")
    public String add(@ModelAttribute Article article){

//        String filename= UUID.randomUUID().toString();
//        if(!file.isEmpty()){
//            try {
//                Files.copy(file.getInputStream(),Paths.get("C:\\server\\image",file.getOriginalFilename()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return "redirect:/fileForm";
        System.out.println(article.toString());
        articleService.add(article);
        return "redirect:/article/create";
    }
}
