package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.blog.Blog;
import com.example.sping_portfolio.blog.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public String viewHomePage(Model model) {
        model.addAttribute("listBlogs", blogService.getAllBlogs());
        return "blog/blogmain";
    }

    @GetMapping("/newBlogForm")
    public String showNewBlogForm(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "blog/newBlogForm";
    }

    @PostMapping("/saveBlog")
    public String saveBlog(@ModelAttribute("blog") Blog blog) {
        blogService.saveBlog(blog);
        return "redirect:/blog";
    }

    @GetMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable(value = "id") long id) {
        blogService.deleteBlogById(id);
        return "redirect:/blog";
    }

}
