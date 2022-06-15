package com.example.sping_portfolio.blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();

    void saveBlog(Blog blog);

    Blog getBlogByID(long id);

    void deleteBlogById(long id);
}
