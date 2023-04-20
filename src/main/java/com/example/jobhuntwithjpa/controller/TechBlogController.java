package com.example.jobhuntwithjpa.controller;

import com.example.jobhuntwithjpa.Service.TechBlogService;
import com.example.jobhuntwithjpa.dto.TechBlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class TechBlogController {
    private final TechBlogService techBlogService;
    @Autowired
    public TechBlogController(TechBlogService techBlogService){
        this.techBlogService = techBlogService;
    }

    @GetMapping()
    public ResponseEntity<List<TechBlogDto>> getTechBlogList(){
        List<TechBlogDto> selectedTechBlog = techBlogService.getTechBlogList();

        return ResponseEntity.status(HttpStatus.OK).body(selectedTechBlog);
    }

    @PostMapping()
    public ResponseEntity<TechBlogDto> createTechBlog(@RequestBody TechBlogDto productDto){
        TechBlogDto techBlogDto = techBlogService.createTechBlog(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(techBlogDto);
    }

    @PutMapping()
    public ResponseEntity<TechBlogDto> changeTechBlog(
            Long id,
            @RequestBody TechBlogDto changeTechBlogDto) throws Exception {
        TechBlogDto techBlogDto = techBlogService.updateTechBlog(
                id,
                changeTechBlogDto
        );

        return ResponseEntity.status(HttpStatus.OK).body(techBlogDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteTechBlog(Long number) throws Exception{
        techBlogService.deleteTechBlogList(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제 되었습니다.");
    }

}
