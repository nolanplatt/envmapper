package com.example.sping_portfolio.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public void saveBlog(Blog blog) {
        this.blogRepository.save(blog);
    }

    @Override
    public Blog getBlogByID(long id) {
        Optional<Blog> optional = blogRepository.findById(id);
        Blog blog = null;
        if (optional.isPresent()) {
            blog = optional.get();
        } else {
            throw new RuntimeException("Blog not found for the id :: " + id);
        }
        return blog;
    }

    @Override
    public void deleteBlogById(long id) {
        blogRepository.deleteById(id);
    }
}
