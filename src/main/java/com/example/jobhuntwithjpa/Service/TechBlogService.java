package com.example.jobhuntwithjpa.Service;

import com.example.jobhuntwithjpa.Entity.TechBlog;
import com.example.jobhuntwithjpa.dto.TechBlogDto;
import com.example.jobhuntwithjpa.repository.TechBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechBlogService {

    TechBlogRepository techBlogRepository;
    @Autowired
    public TechBlogService(TechBlogRepository techBlogRepository) {
        this.techBlogRepository = techBlogRepository;
    }

    public TechBlogDto createTechBlog(TechBlogDto techBlogDto){

        TechBlog newTechBlog = TechBlog.builder()
                .date(techBlogDto.getDate())
                .link(techBlogDto.getLink())
                .logo(techBlogDto.getLogo())
                .preview(techBlogDto.getPreview())
                .build();

        newTechBlog = techBlogRepository.save(newTechBlog);

        TechBlogDto savedTechBlog = new TechBlogDto();
        savedTechBlog.setDate(newTechBlog.getDate());
        savedTechBlog.setLink(newTechBlog.getLink());
        savedTechBlog.setLogo(newTechBlog.getLogo());
        savedTechBlog.setPreview(newTechBlog.getPreview());
        savedTechBlog.setPostId(newTechBlog.getPostId());

        return savedTechBlog;
    }

    public List<TechBlogDto> getTechBlogList(){
        List<TechBlog> selectedTechBlogs = techBlogRepository.findAll();
        List<TechBlogDto> returnTechBlogs = new ArrayList<>();


        for( TechBlog techBlog : selectedTechBlogs){

            TechBlogDto tmp = new TechBlogDto();
            tmp.setDate(techBlog.getDate());
            tmp.setLink(techBlog.getLink());
            tmp.setLogo(techBlog.getLogo());
            tmp.setPreview(techBlog.getPreview());
            tmp.setPostId(techBlog.getPostId());

            returnTechBlogs.add(tmp);
        }

        return returnTechBlogs;
    }

    public TechBlogDto updateTechBlog(Long id, TechBlogDto techBlogDto) throws Exception{

        /*TechBlog newTechBlog = TechBlog.builder()
                .date(techBlogDto.getDate())
                .link(techBlogDto.getLink())
                .logo(techBlogDto.getLogo())
                .preview(techBlogDto.getPreview())
                .build();

        newTechBlog = techBlogRepository.save(newTechBlog);*/

        Optional<TechBlog> newTechBlog = techBlogRepository.findById(id);

        TechBlogDto savedTechBlog;

        if(newTechBlog.isPresent()){
            TechBlog techBlog = TechBlog.builder()
                    .date(techBlogDto.getDate())
                    .link(techBlogDto.getLink())
                    .logo(techBlogDto.getLogo())
                    .preview(techBlogDto.getPreview())
                    .build();
            techBlog = techBlogRepository.save(techBlog);
            savedTechBlog = new TechBlogDto();
            savedTechBlog.setDate(techBlog.getDate());
            savedTechBlog.setLink(techBlog.getLink());
            savedTechBlog.setLogo(techBlog.getLogo());
            savedTechBlog.setPreview(techBlog.getPreview());
            savedTechBlog.setPostId(techBlog.getPostId());
        }else{
            throw new Exception();
        }

        return savedTechBlog;
    }

    public String deleteTechBlogList(Long techBlogId) throws Exception{

        Optional<TechBlog> selectedTechBlog = techBlogRepository.findById(techBlogId);

        if(selectedTechBlog.isPresent()){
            techBlogRepository.delete(selectedTechBlog.get());
        }else{
            throw new Exception();
        }

        return "정상적으로 삭제 되었습니다.";
    }
}
