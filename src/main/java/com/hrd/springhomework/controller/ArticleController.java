package com.hrd.springhomework.controller;

import com.hrd.springhomework.helper.UploadImage;
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

@Controller
public class ArticleController {

    private ArticleService articleService;
    private String currentImage = null;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
        String[] imageNames = {"731cc916-7a4e-4574-9a21-f012e03ad17f.jpg"};
        for (int i = 0; i < 100; i++)
            articleService.add(new Article(i + 1, "Spring", "Sokha", "Cambodia", imageNames[0]));
    }

    @GetMapping({"", "/article"})
    public String index(Model model) {
        System.out.println(articleService.findAll().size());
        model.addAttribute("articles", articleService.findAll());
        return "/articles/index";
    }

    @GetMapping("/article/create")
    public String create(Model model) {
        Article article = new Article();
        article.setId(articleService.getLastId() + 1);
        model.addAttribute("article", article);
        return "/articles/create";
    }

    @DeleteMapping("/article/{id}")
    public String delete(@PathVariable int id) {
        System.out.println(id);
        articleService.remove(id);
        return "redirect:/article";
    }

    @GetMapping("/article/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("article", articleService.find(id));
        return "/articles/edit";
    }

    @GetMapping("/article/show/{id}")
    public String show(@PathVariable int id, Model model) {
        Article article = articleService.find(id);
        System.out.println("ID = " + id);
        System.out.println(article.toString());
        model.addAttribute("article", article);
        return "/articles/show";
    }

    @PostMapping("/article/update")
    public String update(@Valid @ModelAttribute Article article, BindingResult bindingResult, MultipartFile file) {
        System.out.println("binding :" + bindingResult.getModel());
        if (bindingResult.hasErrors()) {
            System.out.println("there is an error");
            return "/articles/edit";
        }
        currentImage = articleService.find(article.getId()).getImage();
        if (!UploadImage.upload(article, file)) {
            article.setImage(currentImage);
        }

        System.out.println(article.toString());
        articleService.update(article);
        return "redirect:/";
    }

    @PostMapping("/article/add")
    public String add(@Valid @ModelAttribute Article article, BindingResult bindingResult, MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "/articles/create";
        }
        UploadImage.upload(article, file);
        articleService.add(article);
        return "redirect:/article/create";
    }
}
