package com.example.jobhuntwithjpa.repository;

import com.example.jobhuntwithjpa.Entity.TechBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechBlogRepository extends JpaRepository<TechBlog, Long> {
}
